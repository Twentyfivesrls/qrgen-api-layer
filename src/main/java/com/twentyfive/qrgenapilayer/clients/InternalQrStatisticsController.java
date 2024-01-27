package com.twentyfive.qrgenapilayer.clients;

import com.twentyfive.twentyfivemodel.models.qrGenModels.QrStatistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "InternalQrStatisticsController", url = "${twentyfive.db.url}/qr_statistics")
public interface InternalQrStatisticsController {

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<QrStatistics> getAllQrStatistics();

    @RequestMapping(method = RequestMethod.GET, value = "/qrStatisticsById/{idQrCode}")
    List<QrStatistics> getQrStatisticsById(@PathVariable String idQrCode);

    @RequestMapping(method = RequestMethod.GET, value = "/qrStatisticsById/desktopSize/{idQrCode}")
    Integer getQrStatisticsByIdDesktopSize(@PathVariable String idQrCode);

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    QrStatistics saveQrStatistics(@RequestBody QrStatistics qrStatistics);
}
