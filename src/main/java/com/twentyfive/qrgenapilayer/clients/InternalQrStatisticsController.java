package com.twentyfive.qrgenapilayer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import twentyfive.twentyfiveadapter.models.qrGenModels.QrStatistics;

import java.util.List;

@FeignClient(name = "InternalQrStatisticsController", url = "${twentyfive.db.url}/qr_statistics")
public interface InternalQrStatisticsController {

    @GetMapping("/all")
    List<QrStatistics> getAllQrStatistics();

    @GetMapping("/qrStatisticsById/{idQrCode}")
    List<QrStatistics> getQrStatisticsById(@PathVariable String idQrCode);

    @GetMapping("/qrStatisticsById/desktopSize/{idQrCode}")
    Integer getQrStatisticsByIdDesktopSize(@PathVariable String idQrCode);

    @PostMapping("/save")
    QrStatistics saveQrStatistics(@RequestBody QrStatistics qrStatistics);
}
