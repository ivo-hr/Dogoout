package com.example.dogoout.mainscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dogoout.R;
import com.example.dogoout.adapters.CardAdapter;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;


public class MatchingFragment extends Fragment {


    public MatchingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matching, container, false);

        //add the view via xml or programmatically
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.swpCrdAdapter);

        // START TEST DATA
        ArrayList<String> characteristics = new ArrayList<>();
        characteristics.add("Playful");
        characteristics.add("Loyal");
        characteristics.add("Friendly");

        ArrayList<URI> dogPhotos = new ArrayList<>();
        try {
            dogPhotos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093803904.jpg?alt=media&token=96be5b20-b156-480b-8fc9-49089e46a77c"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        ArrayList<URI> photos = new ArrayList<>();
        try {
            photos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093706370.jpg?alt=media&token=128b5131-7800-4eb3-8f9e-c8d8e0d04e6f"));
            photos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093658075.jpg?alt=media&token=28a3469d-976a-4174-992f-22abf3107ce5"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        DogBuilder dogBuilder1 = new DogBuilder()
                .withBreed("Beagle")
                .withCharacteristics(characteristics)
                .withName("Rex")
                .withPhotosDog(dogPhotos)
                .withPrompt("What breed do you think I am, and what made you pawsitively choose me?")
                .withPromptAnswer("beagle");

        UserBuilder userBuilder = new UserBuilder()
                .withBirthDate(LocalDate.of(1998, 1, 1))
                .withCountry("US")
                .withDescription("I am an actor")
                .withDog(dogBuilder1.build())
                .withEmail("tanguyvdvd@gmail.com")
                .withFirstname("Brad")
                .withGender("MALE")
                .withPhotosUser(photos)
                .withSurname("Pitt")
                .withPrompt("Describe the perfect day with your dog.")
                .withPromptAnswer("going to the park");

        User user = userBuilder.build();

        // User with No Dogs
        UserBuilder userBuilderNoDogs = new UserBuilder()
                .withBirthDate(LocalDate.of(1985, 5, 15))
                .withCountry("Canada")
                .withDescription("I work in IT and love outdoor activities.")
                .withEmail("janedoe@example.com")
                .withFirstname("Jane")
                .withGender("FEMALE")
                .withPhotosUser(photos)
                .withSurname("Doe")
                .withPrompt("What's your favorite outdoor activity?")
                .withPromptAnswer("I enjoy hiking and camping.");

        User userNoDogs = userBuilderNoDogs.build();

// User with One Dog
        DogBuilder dogBuilderOneDog = new DogBuilder()
                .withBreed("Labrador Retriever")
                .withCharacteristics(characteristics)
                .withName("Charlie")
                .withPhotosDog(dogPhotos)
                .withPrompt("Share a funny story about me and my antics.")
                .withPromptAnswer("Once, Charlie stole my neighbor's shoes and ran around the yard with them!");

        UserBuilder userBuilderOneDog = new UserBuilder()
                .withBirthDate(LocalDate.of(1990, 8, 20))
                .withCountry("UK")
                .withDescription("I'm a chef who loves cooking for my friends and family.")
                .withDog(dogBuilderOneDog.build())
                .withEmail("johnsmith@example.com")
                .withFirstname("John")
                .withGender("MALE")
                .withPhotosUser(photos)
                .withSurname("Smith")
                .withPrompt("What's your dog's favorite treat?")
                .withPromptAnswer("Charlie goes crazy for peanut butter treats!");

        User userOneDog = userBuilderOneDog.build();

// User with Two Dogs
        DogBuilder dogBuilderTwoDogs1 = new DogBuilder()
                .withBreed("Beagle")
                .withCharacteristics(characteristics)
                .withName("Rex")
                .withPhotosDog(dogPhotos)
                .withPrompt("What breed do you think I am, and what made you pawsitively choose me?")
                .withPromptAnswer("beagle");

        DogBuilder dogBuilderTwoDogs2 = new DogBuilder()
                .withBreed("Dachshund")
                .withCharacteristics(characteristics)
                .withName("Bella")
                .withPhotosDog(dogPhotos)
                .withPrompt("Tell us about the first time you met me.")
                .withPromptAnswer("I adopted Bella from a local shelter, and it was love at first sight.");

        UserBuilder userBuilderTwoDogs = new UserBuilder()
                .withBirthDate(LocalDate.of(1982, 3, 10))
                .withCountry("Australia")
                .withDescription("I'm a travel enthusiast who enjoys exploring new cultures.")
                .withDog(dogBuilderTwoDogs1.build())
                .withDog(dogBuilderTwoDogs2.build())
                .withEmail("susanbrown@example.com")
                .withFirstname("Susan")
                .withGender("FEMALE")
                .withPhotosUser(photos)
                .withSurname("Brown")
                .withPrompt("What's your favorite activity to do with your dogs?")
                .withPromptAnswer("Bella and Rex love going on long walks in the park.");

        User userTwoDogs = userBuilderTwoDogs.build();

// Adding users to the ArrayList
        ArrayList<User> users = new ArrayList<>();
        users.add(userNoDogs);
        users.add(userOneDog);
        users.add(userTwoDogs);
        users.add(user);

        // END TEST DATA


        ArrayList<String> t = new ArrayList<>();
        t.add("Playful");
        t.add("Loyal");
        t.add("Friendly");

        //choose your favorite adapter
        ArrayAdapter cardAdapter = new ArrayAdapter(getActivity(), R.layout.card, R.id.txtVNameAge, users);

        //set the listener and the adapter
        flingContainer.setAdapter(cardAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                users.remove(0);
                cardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
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
                // TODO:  Ask for more data from DB here
                users.add(user);

                cardAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                itemsInAdapter += 4;
            }

            @Override
            public void onScroll(float v) {
            }
        });


        return view;
    }
}