package com.example.dogoout.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.dogoout.R;
import com.example.dogoout.adapters.MatchItemAdapter;
import com.example.dogoout.constants.Constants;
import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.dog.DogBuilder;
import com.example.dogoout.domain.preference.Preference;
import com.example.dogoout.domain.user.User;
import com.example.dogoout.domain.user.UserBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;


public class MessagesFragment extends Fragment {


    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        // START TEST DATA
        ArrayList<String> dogCharacteristics = new ArrayList<>();
        dogCharacteristics.add("Social");
        dogCharacteristics.add("Loyal");
        dogCharacteristics.add("Friendly");

        ArrayList<URI> dogPhotos = new ArrayList<>();
        try {
            dogPhotos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093803904.jpg?alt=media&token=96be5b20-b156-480b-8fc9-49089e46a77c"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Dog dog = new DogBuilder()
                .withBreed("German Shepherd")
                .withCharacteristics(dogCharacteristics)
                .withName("Fluffy")
                .withPhotosDog(dogPhotos)
                .withPrompt("What's the most challenging trick Fluffy has learned?")
                .withPromptAnswer("Fluffy learned to balance treats on his nose, and it took some time, but he mastered it!")
                .build();


        ArrayList<String> dogCharacteristics1 = new ArrayList<>();
        dogCharacteristics1.add("Intelligent");
        dogCharacteristics1.add("Energetic");
        dogCharacteristics1.add("Friendly");

        ArrayList<URI> dogPhotos1 = new ArrayList<>();
        try {
            dogPhotos1.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102839589.jpg?alt=media&token=7f0bc611-72b4-493c-b3c1-26bd3d952f7b"));
            dogPhotos1.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102843892.jpg?alt=media&token=16aad5b5-a4b5-4f4c-852b-8163ebbc1316"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Create beagle dog
        Dog dog1 = new DogBuilder()
                .withBreed("Beagle")
                .withCharacteristics(dogCharacteristics1)
                .withName("Rex")
                .withPhotosDog(dogPhotos1)
                .withPrompt("What breed do you think I am, and what made you pawsitively choose me?")
                .withPromptAnswer("beagle")
                .build();


        ArrayList<String> dogCharacteristics2 = new ArrayList<>();
        dogCharacteristics2.add("Intelligent");
        dogCharacteristics2.add("Social");
        dogCharacteristics2.add("Friendly");

        ArrayList<URI> dogPhotos2 = new ArrayList<>();
        try {
            dogPhotos2.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_092152306.png?alt=media&token=a5dba79c-ac13-4086-ac96-d6cae09d0d7a"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Dog dog2 = new DogBuilder()
                .withBreed("Labrador Retriever")
                .withCharacteristics(dogCharacteristics2)
                .withName("Bella")
                .withPhotosDog(dogPhotos2)
                .withPrompt("Tell us about the first time you met me.")
                .withPromptAnswer("I adopted Bella from a local shelter, and it was love at first sight.")
                .build();


        ArrayList<String> dogCharacteristics3 = new ArrayList<>();
        dogCharacteristics3.add("Playful");
        dogCharacteristics3.add("Loyal");
        dogCharacteristics3.add("Affectionate");

        ArrayList<URI> dogPhotos3 = new ArrayList<>();
        try {
            dogPhotos3.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102351104.jpg?alt=media&token=7ad6e4f7-9a9f-4ac6-a8b7-d79aad124115"));
            dogPhotos3.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102355764.jpg?alt=media&token=618347ce-4eb4-4d2d-aba0-46a549cb3b62"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Dog dog3 = new DogBuilder()
                .withBreed("Dachshund")
                .withCharacteristics(dogCharacteristics3)
                .withName("Milo")
                .withPhotosDog(dogPhotos3)
                .withPrompt("What's the most impressive thing Milo has done?")
                .withPromptAnswer("Milo once learned a new trick just by watching a YouTube video!")
                .build();

        ArrayList<String> dogCharacteristics4 = new ArrayList<>();
        dogCharacteristics4.add("Obedient");
        dogCharacteristics4.add("Affable");
        dogCharacteristics4.add("Friendly");

        ArrayList<URI> dogPhotos4 = new ArrayList<>();
        try {
            dogPhotos4.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_092254789.jpg?alt=media&token=d3f1e050-47e1-4b37-a911-c26b6ff32094"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Dog dog4 = new DogBuilder()
                .withBreed("Border Collie")
                .withCharacteristics(dogCharacteristics4)
                .withName("Rocky")
                .withPhotosDog(dogPhotos4)
                .withPrompt("What's the most challenging trick Rocky has learned?")
                .withPromptAnswer("Rocky learned to balance treats on his nose, and it took some time, but he mastered it!")
                .build();


        // MALE
        ArrayList<URI> photos = new ArrayList<>();
        try {
            photos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_103255760.jpg?alt=media&token=af49aab6-b02c-4b55-8e35-5cae633b4e47"));
            photos.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_103300815.jpg?alt=media&token=fdfb9b7c-e20b-496c-b3f5-356a0b4a4956"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // MALE
        ArrayList<URI> photos1 = new ArrayList<>();
        try {
            photos1.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093706370.jpg?alt=media&token=128b5131-7800-4eb3-8f9e-c8d8e0d04e6f"));
            photos1.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231114_093658075.jpg?alt=media&token=28a3469d-976a-4174-992f-22abf3107ce5"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // FEMALE
        ArrayList<URI> photos2 = new ArrayList<>();
        try {
            photos2.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102717501.jpg?alt=media&token=30b7b70a-96b5-49db-9019-879b3f710538"));
            photos2.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102712116.jpg?alt=media&token=b4860bea-9ecc-4838-bb2b-b3821e1b7c1a"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // MALE
        ArrayList<URI> photos3 = new ArrayList<>();
        try {
            photos3.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102225382.jpg?alt=media&token=2359f1b7-8058-4d72-91d7-6e6daf4cc060"));
            photos3.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_102230377.jpg?alt=media&token=1d7d3c13-8e5f-41e2-904a-dee5ddccc65b"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // FEMALE
        ArrayList<URI> photos4 = new ArrayList<>();
        try {
            photos4.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_104124136.jpg?alt=media&token=16e1f7a9-071b-4c85-b2f1-9cb8687c8f8d"));
            photos4.add(new URI("https://firebasestorage.googleapis.com/v0/b/dogoout.appspot.com/o/images%2FIMG_20231127_104119165.jpg?alt=media&token=776509d5-5fd6-42bc-91e2-9257905a40a6"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }



        Preference preference = new Preference();
        preference.setSexPreference(Constants.PREF_H_EVERYONE);
        preference.setDogBreedPreference(Constants.PREF_BREED_ALL);

        // USER 1
        UserBuilder userBuilder = new UserBuilder()
                .withBirthDate(LocalDate.of(1998, 1, 1))
                .withCountry("US")
                .withDescription("I am an actor")
                .withDog(dog4)
                .withEmail("tanguyvdvd@gmail.com")
                .withFirstname("Brad")
                .withGender("MALE")
                .withPreference(preference)
                .withPhotosUser(photos1)
                .withSurname("Pitt")
                .withPrompt("Describe the perfect day with your dog.")
                .withPromptAnswer("going to the park");

        User user = userBuilder.build();

        // USER 2
        UserBuilder userBuilderNoDogs = new UserBuilder()
                .withBirthDate(LocalDate.of(1985, 5, 15))
                .withCountry("Canada")
                .withDescription("I work in IT and love outdoor activities.")
                .withEmail("janedoe@example.com")
                .withFirstname("Psi")
                .withGender("MALE")
                .withDog(dog2)
                .withDog(dog3)
                .withPreference(preference)
                .withPhotosUser(photos)
                .withSurname("Doe")
                .withPrompt("What's your favorite outdoor activity?")
                .withPromptAnswer("I enjoy hiking and camping.");

        User user1 = userBuilderNoDogs.build();


        UserBuilder userBuilderOneDog = new UserBuilder()
                .withBirthDate(LocalDate.of(1990, 8, 20))
                .withCountry("UK")
                .withDescription("I'm a chef who loves cooking for my friends and family.")
                .withDog(dog1)
                .withEmail("johnsmith@example.com")
                .withFirstname("Jane")
                .withGender("FEMALE")
                .withPreference(preference)
                .withPhotosUser(photos2)
                .withSurname("Smith")
                .withPrompt("What's your dog's favorite treat?")
                .withPromptAnswer("Charlie goes crazy for peanut butter treats!");

        User user2 = userBuilderOneDog.build();


        UserBuilder userBuilderTwoDogs = new UserBuilder()
                .withBirthDate(LocalDate.of(1982, 3, 10))
                .withCountry("Australia")
                .withDescription("I'm a travel enthusiast who enjoys exploring new cultures.")
                .withEmail("susanbrown@example.com")
                .withFirstname("Susan")
                .withGender("FEMALE")
                .withPreference(preference)
                .withPhotosUser(photos4)
                .withSurname("Brown")
                .withPrompt("What's your favorite activity to do with your dogs?")
                .withPromptAnswer("Bella and Rex love going on long walks in the park.");

        User user3 = userBuilderTwoDogs.build();


        // Adding users to the ArrayList
        UserBuilder userBuilder5 = new UserBuilder()
                .withBirthDate(LocalDate.of(1993, 7, 8))
                .withCountry("Germany")
                .withDescription("I'm a software engineer who enjoys coding and playing video games.")
                .withEmail("lisa.meyer@example.com")
                .withFirstname("Luis")
                .withGender("MALE")
                .withPreference(preference)
                .withPhotosUser(photos3)
                .withSurname("Meyer")
                .withPrompt("What's your idea of a perfect weekend?")
                .withPromptAnswer("A perfect weekend for me involves coding projects, gaming, and quality time with Fluffy.");

        User user4 = userBuilder5.build();


        // Adding users to the ArrayList
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);


        // END TEST DATA

        // Get user from intent
        User userFromIntent = (User) getActivity().getIntent().getSerializableExtra(Constants.USER_TAG);

        // TODO: get all matches from database of the current user and store them in the users ArrayList

        // Get the RecyclerView
        ListView lvMatches = view.findViewById(R.id.lvMatches);

        // Display the data to the RecyclerView
        lvMatches.setAdapter(new MatchItemAdapter(getActivity(), users));

        return view;
    }
}