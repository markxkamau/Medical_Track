package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoDTO {

    private MultipartFile profilePhoto;
}
