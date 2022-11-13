/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.formktm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Controller
public class ControllerKTM {
    
    @RequestMapping(value = "/showform", method = RequestMethod.POST)
    public String getForm(
        @RequestParam(value = "nama") String isinama,
        @RequestParam(value = "nim") String isinim,
        @RequestParam(value = "tempat") String isitempat,
        @RequestParam("tgl")
        @DateTimeFormat (pattern="yyyy-MM-dd") Date date,
        @RequestParam(value = "jurusan") String isijrsn,
        @RequestParam(value = "email") String isiemail,
        @RequestParam(value = "gambar") MultipartFile img,
            Model kurir
    ) throws IOException{
        SimpleDateFormat newTanggal = new SimpleDateFormat("EE-dd-MMMM-yyyy");
        
        String tanggalku = newTanggal.format(date);
        
        String blob = Base64.encodeBase64String(img.getBytes());
        String gmbr = "data:image/*;base64,".concat(blob);
        
        kurir.addAttribute("pktnama", isinama);
        kurir.addAttribute("pktnim", isinim);
        kurir.addAttribute("pkttempat", isitempat);
        kurir.addAttribute("pkttgl", tanggalku);
        kurir.addAttribute("pktjrsn", isijrsn);
        kurir.addAttribute("pktemail", isiemail);
        kurir.addAttribute("pic", gmbr);
        
        return "viewktm";
    }
    
}
