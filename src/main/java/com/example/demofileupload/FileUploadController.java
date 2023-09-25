package com.example.demofileupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    @PostMapping("/form")
    public String handleFormUpload(MyForm form) {
        System.out.println("Name::::"+form.getName());
        if (!form.getFile().isEmpty()) {
            try {

                byte[] bytes = form.getFile().getBytes();

                System.out.println(bytes.length);
            }catch (Exception e){

            }
            // store the bytes somewhere
            return "uploadSuccess";
        }
        return "redirect:uploadFailure";
    }

    @PostMapping("/form-arr")
    public String handleFormUploadArr(Multipule form) {
        Arrays.stream(form.getMyForm()).map(MyForm::getName).collect(Collectors.toList())
                .forEach(System.out::println);

            Arrays.stream(form.getMyForm()).map(MyForm::getFile).collect(Collectors.toList())
                    .forEach(f -> {
                        try {
                            System.out.println(f.getBytes().length);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        return "redirect:uploadFailure";
    }
}