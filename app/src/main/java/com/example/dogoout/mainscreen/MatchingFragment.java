package com.example.dogoout.mainscreen;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.dogoout.R;
import com.example.dogoout.adapters.CardAdapter;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.dog.DogImpl;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.example.dogoout.domain.user.UserImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class MatchingFragment extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<User> users = new ArrayList<>();


    public MatchingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matching, container, false);

        //add the view via xml or programmatically
        SwipeFlingAdapterView flingContainer = view.findViewById(R.id.swpCrdAdapter);

        User userFromIntent = (User) getActivity().getIntent().getSerializableExtra(Constants.USER_TAG);

        final CardAdapter[] cardAdapter = new CardAdapter[1];

        cardAdapter[0] = new CardAdapter(getActivity(), new ArrayList<>());

        extractUsersBasedOnPreferences(userFromIntent.getPreference().getDogBreedPreference(), userFromIntent.getPreference().getDogOwnerPreference(), userFromIntent.getPreference().getMaxAge(), userFromIntent.getPreference().getMinAge(), userFromIntent.getPreference().getSexPreference(), new OnUsersExtractedListener() {


            @Override
            public void onUsersExtracted(ArrayList<User> users) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cardAdapter[0] = new CardAdapter(getActivity(), users);
                        cardAdapter[0].notifyDataSetChanged();

                    }
                });

                Log.d("DOGS1", users.get(1).toString());

            }

            @Override
            public void onErrorFetchingUsers(Exception e) {
                Log.d("ERRO", e.getMessage());
            }
        });


        //set the listener and the adapter
        flingContainer.setAdapter(cardAdapter[0]);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // TODO: remove user from the ArrayList (from index 0)
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                Log.d("DOGS1", users.remove(0).getDogs().toString());
                cardAdapter[0].notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                // TODO: do nothing here
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getActivity(), "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // TODO: add user to the interactions table
                Toast.makeText(getActivity(), "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // TODO:  Ask for more data from DB (another 5 users) here and store it in the ArrayList
                //users.add(user);

                cardAdapter[0].notifyDataSetChanged();
                Log.d("LIST", "notified");
                itemsInAdapter += 4;
            }

            @Override
            public void onScroll(float v) {
            }
        });

        return view;
    }

    public void extractUsersBasedOnPreferences(String dogBreedPreference, String dogOwnerPreference, int maxAge, int minAge, String sexPreference, OnUsersExtractedListener listener) {
        db.collection("users")
                .whereEqualTo("userPreference.dogBreedPreference", dogBreedPreference)
                .whereEqualTo("userPreference.dogOwnerPreference", dogOwnerPreference)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<User> userArrayList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            User user = createUserFromDocument(document, new OnUserCreatedListener() {
                                @Override
                                public void onUserCreated(User user) {

                                }

                                @Override
                                public void onErrorDownloadingImages() {

                                }
                            });
                            userArrayList.add(user);
                        }
                        listener.onUsersExtracted(userArrayList);
                    } else {
                        listener.onErrorFetchingUsers(task.getException());
                    }
                });
    }

    private UserImpl createUserFromDocument(DocumentSnapshot document, OnUserCreatedListener listener) {
        UserImpl user = new UserImpl();

        if (document.exists()) {
            Map<String, Object> data = document.getData();

            if (data != null) {
                // Assigner les valeurs aux attributs correspondants en utilisant les setters
                user.setFirstname((String) data.get("firstname"));
                user.setSurname((String) data.get("surname"));
                user.setEmail((String) data.get("email"));
                user.setCountry((String) data.get("country"));
                user.setGender((String) data.get("gender"));
                user.setDescription((String) data.get("description"));
                user.setPrompt((String) data.get("prompt"));
                user.setPromptAnswer((String) data.get("promptAnswer"));

                // Convertir la date de naissance de String à LocalDate
                String birthDateString = (String) data.get("birthDate");
                if (birthDateString != null) {
                    LocalDate birthDate = LocalDate.parse(birthDateString);
                    user.setBirthDate(birthDate);
                }

                // Récupérer et définir la préférence utilisateur
                Map<String, Object> preferenceMap = (Map<String, Object>) data.get("userPreference");
                if (preferenceMap != null) {
                    Preference preferenceUser = new Preference(
                            (String) preferenceMap.get("sexPreference"),
                            (String) preferenceMap.get("dogOwnerPreference"),
                            (String) preferenceMap.get("dogOwnerPreference"),
                            ((Long) preferenceMap.get("minAge")).intValue(),
                            ((Long) preferenceMap.get("maxAge")).intValue()

                    );
                    user.setPreferenceSex(preferenceUser);
                }

                ArrayList<String> photosStringList = (ArrayList<String>) data.get("photos");
                ArrayList<URI> userPhotos = new ArrayList<>();
                for (String g : photosStringList
                ) {
                    try {
                        userPhotos.add(new URI(g));
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                }
                user.setPhotosUser(userPhotos);
            }


            ArrayList<Object> dogsList = (ArrayList<Object>) data.get("dogs");

            if (dogsList != null) {

                ArrayList<Dog> dogs = new ArrayList<>();

                for (Object dogObject : dogsList) {
                    if (dogObject instanceof Map) {
                        Map<String, Object> dogMap = (Map<String, Object>) dogObject;

                        String name = (String) dogMap.get("name");
                        String breed = (String) dogMap.get("breed");
                        String prompt = (String) dogMap.get("prompt");
                        String promptAnswer = (String) dogMap.get("promptAnswer");

                        ArrayList<String> characteristics = (ArrayList<String>) dogMap.get("characteristics");

                        // Assuming 'photosDog' is an ArrayList<String> containing photo URLs
                        ArrayList<String> photosDog = (ArrayList<String>) dogMap.get("photosDog");

                        ArrayList<URI> userDogsURI = new ArrayList<>();
                        for (String g : photosDog
                        ) {
                            try {
                                userDogsURI.add(new URI(g));
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }

                        }

                        Dog dog = new DogImpl(name, breed, prompt, promptAnswer, characteristics, userDogsURI);
                        dogs.add(dog);
                    }
                }

                user.setDogs(dogs);
            }
        }

            return user;
    }


    public interface OnUsersExtractedListener {
        void onUsersExtracted(ArrayList<User> users);
        void onErrorFetchingUsers(Exception e);
    }

    public interface OnUserCreatedListener {
        void onUserCreated(User user);
        void onErrorDownloadingImages();
    }

    private void setUsersArray(ArrayList<User> users) {
        this.users = users;
    }




}