package com.twentyfive.qrgenapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.qrgenapilayer.clients.InternalQrCodeController;
import com.twentyfive.twentyfivemodel.dto.qrGenDto.ResponseImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.twentyfiveadapter.models.qrGenModels.QrCodeObject;

import java.util.List;

@RestController
@RequestMapping("/qr_code")
@CrossOrigin(origins = "*")
public class QrCodeController {

    @Value("${fullyenabled.keycloak.role}")
    private String fullyEnabledRole;

    private static final Logger log = LoggerFactory.getLogger(QrCodeController.class);
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
        boolean isFullyEnabled = this.extractIsFullyEnabled();
        QrCodeObject result = internalQrCodeController.saveQrCode(qrCodeObject, username, isFullyEnabled);
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
        boolean isFullyEnabled = this.extractIsFullyEnabled();
        log.info(qrCodeObject.toString());
        QrCodeObject result = internalQrCodeController.download(qrCodeObject, username, isFullyEnabled);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{idQrCode}")
    //TODO: controll qrcodemodel
    public ResponseEntity<Object> updateQrCode(@PathVariable String idQrCode, @RequestBody QrCodeObject qrCodeObject) {
        boolean isFullyEnabled = authenticationService.getRealmRoles().containsKey(fullyEnabledRole);
        QrCodeObject result = internalQrCodeController.updateQrCode(idQrCode, isFullyEnabled, qrCodeObject);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/download/{idQrCode}")
    public ResponseEntity<Object> downloadQrCodeBase64(@PathVariable String idQrCode) {
        ResponseImage result = internalQrCodeController.downloadQrCodeBase64(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    private boolean extractIsFullyEnabled() {
        List<String> a = (List<String>) authenticationService.getRealmRoles().get("roles");
        return a.contains(fullyEnabledRole);
    }
}
