package com.twentyfive.qrgenapilayer.controllers;

import com.twentyfive.authorizationcontroller.services.AuthenticationService;
import com.twentyfive.qrgenapilayer.clients.InternalQrStatisticsController;
import com.twentyfive.twentyfivemodel.models.qrGenModels.QrStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qr_statistics")
@CrossOrigin(origins = "*")
public class QrStatisticsController {

    @Autowired
    private InternalQrStatisticsController qrStatisticsController;


    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllQrStatistics() {
        String username = authenticationService.getUsername();
        List<QrStatistics> result = qrStatisticsController.getAllQrStatistics();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrStatisticsById/{idQrCode}")
    public ResponseEntity<Object> getQrStatisticsById(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        List<QrStatistics> result = qrStatisticsController.getQrStatisticsById(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/qrStatisticsById/desktopSize/{idQrCode}")
    public ResponseEntity<Object> getQrStatisticsByIdDesktopSize(@PathVariable String idQrCode) {
        String username = authenticationService.getUsername();
        Integer result = qrStatisticsController.getQrStatisticsByIdDesktopSize(idQrCode);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveQrStatistics(@RequestBody QrStatistics qrStatistics) {
        String username = authenticationService.getUsername();
        QrStatistics result = qrStatisticsController.saveQrStatistics(qrStatistics);
        return ResponseEntity.ok().body(result);
    }
}
