package is.hi.hbv501g.reithi.Services.Implementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseConfiguration {

    private final FirebaseProperties firebaseProperties;

    public FirebaseConfiguration(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
    }

//    @Bean
//    GoogleCredentials googleCredentials() {
//        try {
//            if (firebaseProperties.getServiceAccount() != null) {
//                try( InputStream is = firebaseProperties.getServiceAccount().getInputStream()) {
//                    return GoogleCredentials.fromStream(is);
//                }
//            }
//            else {
//                // Use standard credentials chain. Useful when running inside GKE
//                return GoogleCredentials.getApplicationDefault();
//            }
//        }
//        catch (IOException ioe) {
//            throw new RuntimeException(ioe);
//        }
//    }

    @Bean
    FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/reithi-firebase-adminsdk-o9ao5-5acbd2f073.json");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
