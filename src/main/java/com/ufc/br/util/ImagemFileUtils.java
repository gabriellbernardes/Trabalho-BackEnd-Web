package com.ufc.br.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


public class ImagemFileUtils {
	public static void salvarImagem(String caminho, MultipartFile imagem) {
		File file = new File(caminho);
		try {
			FileUtils.writeByteArrayToFile(file, imagem.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

public static void deleteImage(String caminho) {

        File file = new File(caminho);

        file.delete();

    }	

}
