package com.twentyfive.qrgenapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.qrgenapilayer.clients.InternalQrCodeController;
import com.twentyfive.twentyfivemodel.dto.qrGenDto.ResponseImage;
import com.twentyfive.twentyfivemodel.models.qrGenModels.QrCodeObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qr_code")
@CrossOrigin(origins = "*")
public class QrCodeController {

    private final InternalQrCodeController internalQrCodeController;
    private final AuthenticationService authenticationService;

    public QrCodeController(InternalQrCodeController internalQrCodeController, AuthenticationService authenticationService) {
        this.internalQrCodeController = internalQrCodeController;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/allByUsername")
    public ResponseEntity<Page<QrCodeObject>> getAllQrCodeByUsername(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        String username = authenticationService.getUsername();
        Page<QrCodeObject> result = internalQrCodeController.getAllQrCodeByUsername(page, size, username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrCodeObjectById/{idQrCode}")
    public ResponseEntity<Object> getQrCodeById(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        QrCodeObject result = internalQrCodeController.getQrCodeById(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        String username = authenticationService.getUsername();
        List<QrCodeObject> result = internalQrCodeController.all(username);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveQrCode(@RequestBody QrCodeObject qrCodeObject) {
        String username = authenticationService.getUsername();
        QrCodeObject result = internalQrCodeController.saveQrCode(qrCodeObject, username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/generateQRCode/{codeText}/{description}")
    public ResponseEntity<Object> generateQrCode(@PathVariable String codeText, @PathVariable String description) {
        byte[] result = internalQrCodeController.generateQrCode(codeText, description);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{idQrCode}")
    public ResponseEntity<QrCodeObject> deleteQrCode(@PathVariable String idQrCode) {
        internalQrCodeController.deleteQrCode(idQrCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generateAndDownloadQRCode")
    public ResponseEntity<QrCodeObject> download(@RequestBody QrCodeObject qrCodeObject) {
        String username = authenticationService.getUsername();
        QrCodeObject result = internalQrCodeController.download(qrCodeObject, username);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{idQrCode}")
    public ResponseEntity<Object> updateQrCode(@PathVariable String idQrCode ,@RequestBody QrCodeObject qrCodeObject) {
        QrCodeObject result = internalQrCodeController.updateQrCode(idQrCode ,qrCodeObject);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/download/{idQrCode}")
    public ResponseEntity<Object> downloadQrCodeBase64(@PathVariable String idQrCode) {
        ResponseImage result = internalQrCodeController.downloadQrCodeBase64(idQrCode);
        return ResponseEntity.ok().body(result);
    }
}
