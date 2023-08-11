package org.launchcode.liftoff.shoefinder.models;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.launchcode.liftoff.shoefinder.constants.ListingConstants.LISTING_IMAGE_DIR_PATH;
import static org.launchcode.liftoff.shoefinder.constants.RegistrationConstants.PROFILE_IMAGE_DIR_PATH;

@Entity
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private MultipartFile profileImage;

    private byte[] imageData;
    @OneToOne
    private UserEntity userEntity;

    public ProfileImage() {}

    public Long getId() {
        return id;
    }

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile imageFile) {
        this.profileImage = profileImage;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(MultipartFile imageFile){
        try {
            this.imageData = imageFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveImageLocally(MultipartFile[] imageFiles){

        String directoryPath = PROFILE_IMAGE_DIR_PATH;

        if(imageFiles!= null && imageFiles.length>0){
            try {
                //Create unique image name using autogenerated id
                String fileName = "image_" + getId() + ".jpg";
                //create file path for image
                Path filePath = Paths.get(directoryPath + "\\" + fileName);
                // Save the file to the images directory within resources
                Files.write(filePath, imageFiles[0].getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception if needed
            }
        }
    }


}
