package com.twentyfive.qrgenapilayer.clients;

import com.twentyfive.twentyfivemodel.dto.qrGenDto.ResponseImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import twentyfive.twentyfiveadapter.models.qrGenModels.QrCodeGroup;
import twentyfive.twentyfiveadapter.models.qrGenModels.QrCodeObject;

import java.util.List;

@FeignClient(name = "InternalQrCodeController", url = "${twentyfive.db.url}/qr_code")
public interface InternalQrCodeController {

    @GetMapping("/allByUsername")
    Page<QrCodeObject> getAllQrCodeByUsername(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam("username") String username);

    @GetMapping("/qrCodeObjectById/{idQrCode}")
    QrCodeObject getQrCodeById(@PathVariable String idQrCode);

    @GetMapping("/all")
    List<QrCodeObject> all(@RequestParam("username") String username);

    @PostMapping("/save")
    QrCodeObject saveQrCode(@RequestBody QrCodeObject qrCodeObject, @RequestParam("username") String username);

    @GetMapping("/generateQRCode/{codeText}/{description}")
    byte[] generateQrCode(@PathVariable String codeText, @PathVariable String description);

    @DeleteMapping("/delete/{idQrCode}")
    QrCodeObject deleteQrCode(@PathVariable String idQrCode);

    @PostMapping("/generateAndDownloadQRCode")
    QrCodeObject download(@RequestBody QrCodeObject qrCodeObject, @RequestParam("username") String username);

    @PutMapping("/update/{idQrCode}")
    QrCodeObject updateQrCode(@PathVariable String idQrCode, @RequestBody QrCodeObject qrCodeObject);

    @GetMapping("/download/{idQrCode}")
    ResponseImage downloadQrCodeBase64(@PathVariable String idQrCode);

    @PostMapping("/generateQrGroup")
    List<QrCodeGroup> generateQrGroup(@RequestParam("username") String username, @RequestParam("ownerId") String ownerId);
}
