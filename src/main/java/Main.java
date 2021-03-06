

import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {

        String key = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("key"))) {
            key = reader.readLine();
            System.out.println(key);
        } catch (Exception ioe) {
            System.out.println("No key file found, or could not read key");
            System.exit(-1);
        }

        GeoApiContext context = new GeoApiContext().setApiKey(key);

        LatLng mctcLatLng = new LatLng(44.973074, -93.2833356);
        ElevationResult[] results = ElevationApi.getByPoints(context, mctcLatLng).await();
        if (results.length >= 1) {
            ElevationResult mctcElevation = results[0];
            System.out.println("The elevation of MCTC above sea level is " + mctcElevation.elevation + " meters");
            System.out.println(String.format("The elevation is %.2f meters.", mctcElevation.elevation));
            System.out.println();
        }


        LatLng myHomeLatLng = new LatLng(44.99770440, -93.23999710);
        ElevationResult[] result = ElevationApi.getByPoints(context, myHomeLatLng).await();
        if (result.length >= 1) {
            ElevationResult myHomeElevation = result[0];
            System.out.println("The elevation of myHomeElevation above sea level is " + myHomeElevation.elevation + " meters");
            System.out.println(String.format("The elevation is %.2f meters.", myHomeElevation.elevation));
            System.out.println();
        }


        GeocodingResult[] results1 = GeocodingApi.geocode(context, "Allentown").await();
        for (int i = 0 ; i < results1.length ; i++) {
            GeocodingResult locationResult = results1[i];
            System.out.println("location address = " + locationResult.formattedAddress);
            System.out.println("results1 length = " + results1.length);
            System.out.println("location placeid = " + locationResult.placeId);
            System.out.println("location geometry = " + locationResult.geometry.location);
            System.out.println();
        }

        GeocodingResult[] results2 = GeocodingApi.reverseGeocode(context, myHomeLatLng).await();
        for (int i = 0 ; i < results2.length ; i++) {
            GeocodingResult locationResult = results2[i];
            System.out.println("location address = " + locationResult.formattedAddress);
            System.out.println("location placeid = " + locationResult.placeId);
            System.out.println("location geometry = " + locationResult.geometry.location);
            System.out.println();
        }

        GeocodingResult[] results3 = GeocodingApi.geocode(context, "726 Buchanan St NE, Minneapolis, MN 55413, USA").await();
        System.out.println();
        for (int i = 0 ; i < results3.length ; i++) {
            GeocodingResult locationResult = results3[i];
            System.out.println("location address = " + locationResult.formattedAddress);
            System.out.println("results3 length = " + results1.length);
            System.out.println("location placeid = " + locationResult.placeId);
            System.out.println("location geometry = " + locationResult.geometry.location);
        }


    } // end main method

} // end Main class
