package com.twentyfive.qrgenapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.qrgenapilayer.clients.InternalQrCodeController;
import com.twentyfive.twentyfivemodel.dto.qrGenDto.ResponseImage;
import com.twentyfive.twentyfivemodel.models.qrGenModels.QrCodeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/qr_code")
@CrossOrigin(origins = "*")
public class QrCodeController {

    @Autowired
    private InternalQrCodeController qrCodeController;


    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/allByUsername")
    public ResponseEntity<Page<QrCodeObject>> getAllQrCodeByUsername(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        String username = authenticationService.getUsername();
        Page<QrCodeObject> result = qrCodeController.getAllQrCodeByUsername(page, size, username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrCodeObjectById/{idQrCode}")
    public ResponseEntity<Object> getQrCodeById(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        QrCodeObject result = qrCodeController.getQrCodeById(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        String username = authenticationService.getUsername();
        List<QrCodeObject> result = qrCodeController.all(username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveQrCode(@RequestBody QrCodeObject qrCodeObject) {
        String username = authenticationService.getUsername();
        QrCodeObject result = qrCodeController.saveQrCode(qrCodeObject, username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/generateQRCode/{codeText}/{description}")
    public ResponseEntity<Object> generateQrCode(@PathVariable String codeText, @PathVariable String description) {
        String username = authenticationService.getUsername();
        byte[] result = qrCodeController.generateQrCode(codeText, description);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{idQrCode}")
    public ResponseEntity<QrCodeObject> deleteQrCode(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        qrCodeController.deleteQrCode(idQrCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generateAndDownloadQRCode")
    public ResponseEntity<Object> download(@RequestBody QrCodeObject qrCodeObject) {
        String username = authenticationService.getUsername();
        Object result = qrCodeController.download(qrCodeObject, username);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{idQrCode}")
    public ResponseEntity<Object> updateQrCode(@PathVariable String idQrCode ,@RequestBody QrCodeObject qrCodeObject) {
        String username = authenticationService.getUsername();
        QrCodeObject result = qrCodeController.updateQrCode(idQrCode ,qrCodeObject);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/download/{idQrCode}")
    public ResponseEntity<Object> downloadQrCodeBase64(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        ResponseImage result = qrCodeController.downloadQrCodeBase64(idQrCode);
        return ResponseEntity.ok().body(result);
    }
}
