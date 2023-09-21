package com.twentyfive.qrgenapilayer.clients;

import com.twentyfive.twentyfivemodel.dto.ResponseImage;
import com.twentyfive.twentyfivemodel.models.qrGenModels.QrCodeObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "InternalQrCodeController", url = "http://tomcat-twentyfive-db:8091/twentyfive-db/qr_code")
public interface InternalQrCodeController {

    @RequestMapping(method = RequestMethod.GET, value = "/allByIdUser")
    Page<QrCodeObject> getAllQrCodeByIdUser(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @RequestMapping(method = RequestMethod.GET, value = "/qrCodeObjectById/{idQrCode}")
    QrCodeObject getQrCodeById(@PathVariable String idQrCode);

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    QrCodeObject saveQrCode(@RequestBody QrCodeObject qrCodeObject);

    @RequestMapping(method = RequestMethod.GET, value = "/generateQRCode/{codeText}/{description}")
    byte[] generateQrCode(@PathVariable String codeText, @PathVariable String description);

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteQrCode/{idQrCode}")
    QrCodeObject deleteQrCode(@PathVariable String idQrCode);

    @RequestMapping(method = RequestMethod.POST, value = "/generateAndDownloadQRCode")
    QrCodeObject download(@RequestBody QrCodeObject qrCodeObject);

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{idQrCode}")
    QrCodeObject updateQrCode(@PathVariable String idQrCode ,@RequestBody QrCodeObject qrCodeObject);

    @RequestMapping(method = RequestMethod.GET, value = "/download/{idQrCode}")
    ResponseImage downloadQrCodeBase64(@PathVariable String idQrCode);
}
