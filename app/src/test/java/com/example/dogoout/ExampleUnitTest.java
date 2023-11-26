package com.example.dogoout;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import android.net.Uri;

import com.example.dogoout.validation.Validator;

import java.net.URI;
import java.util.ArrayList;




public class ExampleUnitTest {
    @Test //Test if the email is valid
    public void email_isCorrect() {
        assertTrue(Validator.isValidEmail("example@email.com"));
        assertTrue(Validator.isValidEmail("ExAmPlE@EMAIL.COM"));
        assertTrue(Validator.isValidEmail("3X.AMP1e@3mai1.org"));
        assertTrue(Validator.isValidEmail("ex.am21e@email123123231.com"));
        assertTrue(Validator.isValidEmail("exam.p13.422362@email.com"));
    }
    @Test //Test if the email is invalid
    public void email_isIncorrect() {
        assertFalse(Validator.isValidEmail("exampleemail.com"));
        assertFalse(Validator.isValidEmail("example@"));
        assertFalse(Validator.isValidEmail("example@."));
        assertFalse(Validator.isValidEmail("e##xxample@.com"));
        assertFalse(Validator.isValidEmail("exa@mple@.com"));
    }
    @Test //Test if the password is valid
    public void password_isCorrect() {
        assertTrue(Validator.isValidPassword("Password123"));
        assertTrue(Validator.isValidPassword("Password123!"));
        assertTrue(Validator.isValidPassword("Password123!@#"));
        assertTrue(Validator.isValidPassword("=dD123!@#"));
        assertTrue(Validator.isValidPassword("1231qQ!@#"));
    }
    @Test //Test if the password is invalid
    public void password_isIncorrect() {
        assertFalse(Validator.isValidPassword("password123"));
        assertFalse(Validator.isValidPassword("PASSWORD123"));
        assertFalse(Validator.isValidPassword("password"));
        assertFalse(Validator.isValidPassword("123123"));
        assertFalse(Validator.isValidPassword("123123!@#"));
    }
    @Test //Test if the name is valid
    public void name_isCorrect() {
        assertTrue(Validator.isValidName("John"));
        assertTrue(Validator.isValidName("John Doe"));
        assertTrue(Validator.isValidName("John Doe Doe"));
        assertTrue(Validator.isValidName("John Doe Doe Doe"));
        assertTrue(Validator.isValidName("John Doe Doe Doe Doe"));
    }
    @Test //Test if the name is invalid
    public void name_isIncorrect() {
        assertFalse(Validator.isValidName("J"));
        assertFalse(Validator.isValidName("@"));
        assertFalse(Validator.isValidName("John1"));
        assertFalse(Validator.isValidName("John Doe1"));
        assertFalse(Validator.isValidName("John Doe Doe@"));
    }

    /*@Mock
    android.net.Uri mockUri = Mockito.mock(android.net.Uri.class);
    @Test //Test if the photo array is valid
    public void photo_isCorrect() {
        //Mock the uri class
        when (mockUri.parse(Mockito.anyString())).thenReturn(mockUri);
        //Create an array of photos
        ArrayList<URI> photos = new ArrayList<>();
        //Add 1 photo
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add 2 photos
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add 3 photos
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.jpg"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.jpeg"));
        assertTrue(Validator.isValidPhoto(photos));
    }
    @Test //Test if the photo array is invalid
    public void photo_isIncorrect() {
        //Mock the uri class
        when (mockUri.parse(Mockito.anyString())).thenReturn(mockUri);
        //Create an array of photos
        ArrayList<URI> photos = new ArrayList<>();
        //Add 1 photo
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add 2 photos
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add 3 photos
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.jpg"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.jpeg"));
        assertTrue(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.bmp"));
        assertFalse(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.webp"));
        assertFalse(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.webp"));
        assertFalse(Validator.isValidPhoto(photos));
        //Add a photo with a different format
        photos.add(URI.create("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.webp"));
        assertFalse(Validator.isValidPhoto(photos));
    }*/

    //Test if the text is valid
    @Test
    public void text_isCorrect() {
        String maxText = "";
        for (int i = 0; i < 10000; i++) {
            maxText += "a";
        }
        assertTrue(Validator.isValidTextLength("     sdfdsf     "));
        assertTrue(Validator.isValidTextLength("H"));
        assertTrue(Validator.isValidTextLength("\n0\n"));
        assertTrue(Validator.isValidTextLength("Hello World World World"));
        assertTrue(Validator.isValidTextLength(maxText));
        //With min and max
        assertTrue(Validator.isValidTextLength("Hello", 2, 10));
        assertTrue(Validator.isValidTextLength("Hello World\n\n", 2, 11));
        assertTrue(Validator.isValidTextLength("Hello World World", 2, 30));
        assertTrue(Validator.isValidTextLength("          s   d      ", 2, 50));
        assertTrue(Validator.isValidTextLength("Hello World World World World", 2, 100));
    }

    //Test if the text is invalid
    @Test
    public void text_isIncorrect() {
        String maxText = "";
        for (int i = 0; i < 10001; i++) {
            maxText += "a";
        }
        assertFalse(Validator.isValidTextLength(null));
        assertFalse(Validator.isValidTextLength("\n\n"));
        assertFalse(Validator.isValidTextLength("    "));
        assertFalse(Validator.isValidTextLength(maxText));
        assertFalse(Validator.isValidTextLength(""));
        //With min and max
        assertFalse(Validator.isValidTextLength("H", 2, 10));
        assertFalse(Validator.isValidTextLength("Hello World", 20, 40));
        assertFalse(Validator.isValidTextLength("Hello World World", 2, 3));
        assertFalse(Validator.isValidTextLength("Hello World World World", 0, 0));
        assertFalse(Validator.isValidTextLength("    ", 2, 100));
    }

    //Test if the age is valid
    @Test
    public void age_isCorrect(){
        //With min and max
        assertTrue(Validator.isValidAge(18, 100));
        assertTrue(Validator.isValidAge(18, 18));
        assertTrue(Validator.isValidAge(18, 19));
        assertTrue(Validator.isValidAge(18, 45));
        assertTrue(Validator.isValidAge(99, 100));
        //Without min and max
        assertTrue(Validator.isValidAge(18));
        assertTrue(Validator.isValidAge(45));
        assertTrue(Validator.isValidAge(100));
        assertTrue(Validator.isValidAge(74));
        assertTrue(Validator.isValidAge(31));
        //With LocalDate
        assertTrue(Validator.isValidAge(java.time.LocalDate.of(2003, 8, 4)));
        assertTrue(Validator.isValidAge(java.time.LocalDate.of(2004, 3, 31)));
        assertTrue(Validator.isValidAge(java.time.LocalDate.of(1945, 12, 12)));
        assertTrue(Validator.isValidAge(java.time.LocalDate.of(1969, 6, 9)));
        assertTrue(Validator.isValidAge(java.time.LocalDate.of(2000, 1, 1)));
    }
    //Test if the age is invalid
    @Test
    public void age_isIncorrect() {
        //With min and max
        assertFalse(Validator.isValidAge(17, 100));
        assertFalse(Validator.isValidAge(18, 17));
        assertFalse(Validator.isValidAge(18, 101));
        assertFalse(Validator.isValidAge(4, 7));
        assertFalse(Validator.isValidAge(100, 18));
        //Without min and max
        assertFalse(Validator.isValidAge(1));
        assertFalse(Validator.isValidAge(17));
        assertFalse(Validator.isValidAge(10));
        assertFalse(Validator.isValidAge(101));
        assertFalse(Validator.isValidAge(1001241245));
        //With LocalDate
        assertFalse(Validator.isValidAge(java.time.LocalDate.of(2007, 8, 5)));
        assertFalse(Validator.isValidAge(java.time.LocalDate.of(1834, 3, 30)));
        assertFalse(Validator.isValidAge(java.time.LocalDate.of(1901, 1, 13)));
        assertFalse(Validator.isValidAge(java.time.LocalDate.of(0, 1, 1)));
        assertFalse(Validator.isValidAge(java.time.LocalDate.of(2069, 6, 9)));
    }
    //Test if the characteristics are valid
    @Test
    public void characteristics_isCorrect(){
        ArrayList<String> characteristics = new ArrayList<>();

        characteristics.add("Friendly");
        assertTrue(Validator.isValidCharacteristics(characteristics, 1, 10));
        characteristics.add("Cute");
        assertTrue(Validator.isValidCharacteristics(characteristics, 2, 10));
        characteristics.add("Loyal");
        assertTrue(Validator.isValidCharacteristics(characteristics, 3, 10));
        characteristics.add("Playful");
        assertTrue(Validator.isValidCharacteristics(characteristics, 4, 10));
        for (int i = 0; i < 30; i++) {
            characteristics.add("Smart");
        }
        assertTrue(Validator.isValidCharacteristics(characteristics,33 , 34));
        //Without min and max
        characteristics.clear();
        characteristics.add("Friendly");
        assertTrue(Validator.isValidCharacteristics(characteristics));
        characteristics.add("Cute");
        assertTrue(Validator.isValidCharacteristics(characteristics));
        characteristics.add("Loyal");
        assertTrue(Validator.isValidCharacteristics(characteristics));
        characteristics.add("Playful");
        assertTrue(Validator.isValidCharacteristics(characteristics));
        for (int i = 0; i < 6; i++) {
            characteristics.add("Smart");
        }
        assertTrue(Validator.isValidCharacteristics(characteristics));
    }
}