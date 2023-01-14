package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;


    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        Image image = new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);

        image.setBlog(blog);

        List<Image> list=blog.getImageList();

        if(list==null) {
            list= new ArrayList<>();
        }
        list.add(image);

        blog.setImageList(list);

        imageRepository2.save(image);
        blogRepository.save(blog);
        return image;
    }

    public void deleteImage(Image image){

        // Image image=findById(image1.getId());
        //888888888888888888
        //if(imageRepository2.existsById(image.getId())){
//
//            Blog blog = image.getBlog();
//
//            List<Image> list = blog.getImageList();
//
//            list.remove(image);
//
//            blog.setImageList(list);
//
//            imageRepository2.delete(image);
//       // }
        imageRepository2.delete(image);
    }

    public Image findById(int id) {

        Image image=new Image();
        //if(imageRepository2.existsById(id)) {
        image = imageRepository2.findById(id).get();
        // deleteImage(image);
        // }
        return image;
    }

    public int countImagesInScreen(Image image, String screenDimensions) {

        if(screenDimensions.split("X").length==2 || Objects.nonNull(image)){
            Integer maxLength= Integer.parseInt(screenDimensions.split("X")[0])/Integer.parseInt(image.getDimensions().split("X")[0]);
            Integer maxWidth= Integer.parseInt(screenDimensions.split("X")[1])/Integer.parseInt(image.getDimensions().split("X")[1]);

            return  maxWidth*maxLength;
        }
        return  0;
    }
}