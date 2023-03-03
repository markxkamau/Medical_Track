package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.AddPhotoDto;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Photo;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PatientRepository patientRepository;

    public byte[] getProfileImage(Long id) {
        return photoRepository.findById(id).get().getProfilePhoto();
    }

    public void addNewPhoto(AddPhotoDto addPhotoDto) {
        Patient patient = patientRepository.findById(addPhotoDto.getPatientId()).get();
        Photo photo = new Photo(
                addPhotoDto.getId(),
                addPhotoDto.getProfilePhoto(),
                patient
        );
        photoRepository.save(photo);
        patient.setPhotoAvailable(true);
        patientRepository.save(patient);
    }

    public Photo getPhotoInfoByPatientId(Long id) {
        return photoRepository.findByPatientId(id);
    }
}
