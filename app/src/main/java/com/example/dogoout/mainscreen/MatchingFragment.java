package com.example.dogoout.mainscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dogoout.R;
import com.example.dogoout.adapters.CardAdapter;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.dog.DogImpl;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserImpl;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


public class MatchingFragment extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<User> users = new ArrayList<>();

    final CardAdapter[] cardAdapter = new CardAdapter[]{new CardAdapter(getActivity(), users)};

    SwipeFlingAdapterView flingContainer;

    public MatchingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matching, container, false);

        //add the view via xml or programmatically
        flingContainer = view.findViewById(R.id.swpCrdAdapter);

        User userFromIntent = (User) getActivity().getIntent().getSerializableExtra(Constants.USER_TAG);

        final CardAdapter[] cardAdapter = new CardAdapter[1];
        cardAdapter[0] = new CardAdapter(getActivity(), new ArrayList<>());
        flingContainer.setAdapter(cardAdapter[0]);


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // TODO: remove user from the ArrayList (from index 0)
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                users.remove(0);
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
                extractUsersBasedOnPreferences(userFromIntent.getPreference().getDogOwnerPreference(), userFromIntent.getPreference().getMaxAge(), userFromIntent.getPreference().getMinAge(), userFromIntent.getPreference().getSexPreference(), new OnUsersExtractedListener() {
                    @Override
                    public void onUsersExtracted(ArrayList<User> users) {
                        // Display the users into the cardAdapter
                        MatchingFragment.this.users.addAll(users);
                        cardAdapter[0] = new CardAdapter(getActivity(), MatchingFragment.this.users);
                        flingContainer.setAdapter(cardAdapter[0]);
                        cardAdapter[0].notifyDataSetChanged();
                    }

                    @Override
                    public void onErrorFetchingUsers(Exception e) {
                        Log.d("ERRO", e.getMessage());
                    }
                });

                Log.d("LIST", "notified");
            }

            @Override
            public void onScroll(float v) {
            }
        });

        return view;
    }

    public void extractUsersBasedOnPreferences(String dogOwnerPreference, int maxAge, int minAge, String sexPreference, OnUsersExtractedListener listener) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, -maxAge);
        Date maxBirthDate = calendar.getTime();
        String maxBirthDateString = sdf.format(maxBirthDate);

        Log.d("max", String.valueOf(maxAge) + "   " + maxBirthDateString);

        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, -minAge);
        Date minBirthDate = calendar.getTime();
        String minBirthDateString = sdf.format(minBirthDate);

        Log.d("min", String.valueOf(minAge) + "   " + minBirthDateString);

        Query query = db.collection("users");

        if (sexPreference.equals("FEMALE")) {
            query = query.whereEqualTo("gender","FEMALE");

        }
        if (sexPreference.equals("MALE")) {
            query = query.whereEqualTo("gender","MALE");
        }

        if (sexPreference.equals("OTHERS")) {
            query = query.whereEqualTo("gender","OTHER");
        }

        if (dogOwnerPreference.equals("OWNERS")) {
            query = query.whereNotEqualTo("dogs",false);
        }

                query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<User> userArrayList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                            LocalDate date1 = LocalDate.parse(document.get("birthDate").toString(), dateFormatter);
                            LocalDate max = LocalDate.parse(maxBirthDateString, dateFormatter);
                            LocalDate min = LocalDate.parse(minBirthDateString, dateFormatter);

                            if (dogOwnerPreference.equals("LOVERS")) {
                                if (!document.contains("dogs")) {
                                    if (date1.isAfter(max) && date1.isBefore(min)) {


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
                                }
                            } else {


                                if (date1.isAfter(max) && date1.isBefore(min)) {


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
                            }
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


}