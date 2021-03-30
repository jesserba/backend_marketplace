package com.example.backend_market_place.services.produit;

import com.example.backend_market_place.metier.produit.ImageInterface;
import com.example.backend_market_place.models.produit.Image;
import com.example.backend_market_place.models.produit.Produit;
import com.example.backend_market_place.repository.produit.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
@Service
public class ImageService implements ImageInterface {
    @Autowired
    ImageRepository imageRepository;

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    @Override
    public Image saveImage (MultipartFile file)throws IOException{
        Image img = new Image();
        img.setNom(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setTaille(compressBytes(file.getBytes()));
       imageRepository.save(img);
       return img;
    }

    @Override
    public List<Image> getImage(Produit produit) throws IOException {
       List<Image> images = imageRepository.findByProduit(produit);
        for(int i=0; i< images.size();i++)
        {
            byte[] nt = decompressBytes(images.get(i).getTaille());
            images.get(i).setTaille(nt);
        }
        return images;
    }

}
