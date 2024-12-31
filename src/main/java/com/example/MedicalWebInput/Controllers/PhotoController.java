package com.example.MedicalWebInput.Controllers;

public class PhotoController {
//    @GetMapping("/patient/{id}/profile_photo")
//    public ResponseEntity<AddPhotoDto> getPatientProfilePhoto(@PathVariable Long id, @NotNull HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        if (session == null || session.getAttribute("patient_info") == null) {
//            // If the user is not logged in, redirect them to the login page
//            return ResponseEntity.ok(null);
//        }
//        BasicPatientDto patient = (BasicPatientDto) session.getAttribute("patient_info");
//        AddPhotoDto addPhotoDto = new AddPhotoDto();
//        addPhotoDto.setPatientId(patientService.getPatientByEmail(patient.getEmail()).getId());
//        return ResponseEntity.ok(addPhotoDto);
//    }
//    @PostMapping("/upload_photo")
//    public ResponseEntity<AddPhotoDto> handleFileUpload(@RequestBody AddPhotoDto addPhotoDto, @NotNull RedirectAttributes redirectAttributes) throws IOException {
//        photoService.checkForCurrentPhoto(addPhotoDto.getPatientId());
//        Photo photo = photoService.addNewPhoto(addPhotoDto);
//        String image = photoService.getImage(photo.getId());
//        if (image.equals(null)) {
//            redirectAttributes.addFlashAttribute("upload_error", "Check your uploaded photo");
//            return null;
//        }
//        return ResponseEntity.ok(addPhotoDto);
//    }


}
