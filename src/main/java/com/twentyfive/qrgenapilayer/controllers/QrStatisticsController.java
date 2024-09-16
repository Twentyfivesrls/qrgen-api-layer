package com.twentyfive.qrgenapilayer.controllers;

import com.twentyfive.authorizationflow.services.AuthenticationService;
import com.twentyfive.qrgenapilayer.clients.InternalQrStatisticsController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.twentyfiveadapter.models.qrGenModels.QrStatistics;

import java.util.List;

@RestController
@RequestMapping("/qr_statistics")
@CrossOrigin(origins = "*")
public class QrStatisticsController {

    private final InternalQrStatisticsController internalQrStatisticsController;
    private final AuthenticationService authenticationService;

    public QrStatisticsController(InternalQrStatisticsController internalQrStatisticsController, AuthenticationService authenticationService) {
        this.internalQrStatisticsController = internalQrStatisticsController;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllQrStatistics() {
        String username = authenticationService.getUsername();
        List<QrStatistics> result = internalQrStatisticsController.getAllQrStatistics();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrStatisticsById/{idQrCode}")
    public ResponseEntity<Object> getQrStatisticsById(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        List<QrStatistics> result = internalQrStatisticsController.getQrStatisticsById(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrStatisticsById/desktopSize/{idQrCode}")
    public ResponseEntity<Object> getQrStatisticsByIdDesktopSize(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        Integer result = internalQrStatisticsController.getQrStatisticsByIdDesktopSize(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveQrStatistics(@RequestBody QrStatistics qrStatistics) {
        String username = authenticationService.getUsername();
        QrStatistics result = internalQrStatisticsController.saveQrStatistics(qrStatistics);
        return ResponseEntity.ok().body(result);
    }
}
