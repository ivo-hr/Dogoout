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
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.preference.Preference;
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
        SwipeFlingAdapterView flingContainer = view.findViewById(R.id.swpCrdAdapter);

        // START TEST DATA
        ArrayList<String> characteristics = new ArrayList<>();
        characteristics.add("Playful");
        characteristics.add("Loyal");
        characteristics.add("Friendly");

        ArrayList<URI> dogPhotos = new ArrayList<>();
        try {
            dogPhotos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093803904.jpg?alt=media&token=96be5b20-b156-480b-8fc9-49089e46a77c"));
            dogPhotos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093857374.jpg?alt=media&token=266f20a7-557a-445f-92a4-f46a490a97ed"));
            dogPhotos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093857374.jpg?alt=media&token=266f20a7-557a-445f-92a4-f46a490a97ed"));
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

        Preference preference = new Preference();
        preference.setSexPreference(Constants.PREF_OTHER);
        preference.setDogBreedPreference(Constants.PREF_BREED_ALL);

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
                .withPreference(preference)
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
                .withPreference(preference)
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
                .withPreference(preference)
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
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Brown")
                .withPrompt("What's your favorite activity to do with your dogs?")
                .withPromptAnswer("Bella and Rex love going on long walks in the park.");

        User userTwoDogs = userBuilderTwoDogs.build();


        // Adding users to the ArrayList
        UserBuilder userBuilder5 = new UserBuilder()
                .withBirthDate(LocalDate.of(1993, 7, 8))
                .withCountry("Germany")
                .withDescription("I'm a software engineer who enjoys coding and playing video games.")
                //.withDog(dogBuilder5.build())
                .withEmail("lisa.meyer@example.com")
                .withFirstname("Lisa")
                .withGender("FEMALE")
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Meyer")
                .withPrompt("What's your idea of a perfect weekend?")
                .withPromptAnswer("A perfect weekend for me involves coding projects, gaming, and quality time with Fluffy.");

        User user5 = userBuilder5.build();

// User 6
        DogBuilder dogBuilder6 = new DogBuilder()
                .withBreed("Siberian Husky")
                .withCharacteristics(characteristics)
                .withName("Luna")
                .withPhotosDog(dogPhotos)
                .withPrompt("Share a memorable adventure you've had with me.")
                .withPromptAnswer("Luna and I once went on a camping trip, and she loved howling along with the night sounds.");

        UserBuilder userBuilder6 = new UserBuilder()
                .withBirthDate(LocalDate.of(1988, 12, 20))
                .withCountry("USA")
                .withDescription("I'm a graphic designer with a passion for outdoor activities.")
                .withDog(dogBuilder6.build())
                .withEmail("david.smith@example.com")
                .withFirstname("David")
                .withGender("MALE")
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Smith")
                .withPrompt("What's your favorite season, and how do you and Luna enjoy it?")
                .withPromptAnswer("My favorite season is winter, and Luna and I enjoy sledding and playing in the snow.");

        User user6 = userBuilder6.build();

// User 7 (No Dogs)
        UserBuilder userBuilderNoDogs2 = new UserBuilder()
                .withBirthDate(LocalDate.of(1995, 3, 15))
                .withCountry("France")
                .withDescription("I'm a student studying environmental science and photography.")
                .withEmail("alex.jones@example.com")
                .withFirstname("Alex")
                .withGender("NON_BINARY")
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Jones")
                .withPrompt("Share a hobby or interest that you're passionate about.")
                .withPromptAnswer("I'm passionate about nature photography and spend my weekends exploring and capturing beautiful landscapes.");

        User userNoDogs2 = userBuilderNoDogs2.build();

        // User 8 with Three Dogs
        DogBuilder dogBuilder8_1 = new DogBuilder()
                .withBreed("German Shepherd")
                .withCharacteristics(characteristics)
                .withName("Rocky")
                .withPhotosDog(dogPhotos)
                .withPrompt("What's the most challenging trick Rocky has learned?")
                .withPromptAnswer("Rocky learned to balance treats on his nose, and it took some time, but he mastered it!");

        DogBuilder dogBuilder8_2 = new DogBuilder()
                .withBreed("Shih Tzu")
                .withCharacteristics(characteristics)
                .withName("Coco")
                .withPhotosDog(dogPhotos)
                .withPrompt("Share a funny or quirky habit that Coco has.")
                .withPromptAnswer("Coco has a habit of barking at her own reflection in the mirror, thinking it's another dog!");

        DogBuilder dogBuilder8_3 = new DogBuilder()
                .withBreed("Border Collie")
                .withCharacteristics(characteristics)
                .withName("Milo")
                .withPhotosDog(dogPhotos)
                .withPrompt("What's the most impressive thing Milo has done?")
                .withPromptAnswer("Milo once learned a new trick just by watching a YouTube video!");

        UserBuilder userBuilder8 = new UserBuilder()
                .withBirthDate(LocalDate.of(1980, 6, 25))
                .withCountry("Canada")
                .withDescription("I'm a veterinarian who loves spending time with my three amazing dogs.")
                .withDog(dogBuilder8_1.build())
                .withDog(dogBuilder8_2.build())
                .withDog(dogBuilder8_3.build())
                .withEmail("vetperson@example.com")
                .withFirstname("Alexandra")
                .withGender("FEMALE")
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Peterson")
                .withPrompt("What's the best part about being a pet owner?")
                .withPromptAnswer("The unconditional love and joy my dogs bring into my life every day.");

        User user8 = userBuilder8.build();


// Adding users to the ArrayList
        ArrayList<User> users = new ArrayList<>();
        users.add(userNoDogs);
        users.add(userOneDog);
        users.add(userTwoDogs);

        // END TEST DATA

        // TODO: Get users from DB here (list of idk... 5 users) and store them in the ArrayList
        // TODO: Firstly check if there are any users under interaction table


        //choose your favorite adapter
        CardAdapter cardAdapter = new CardAdapter(getActivity(), users);

        //set the listener and the adapter
        flingContainer.setAdapter(cardAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // TODO: remove user from the ArrayList (from index 0)
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                Log.d("DOGS1", users.remove(0).getDogs().toString());
                cardAdapter.notifyDataSetChanged();
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