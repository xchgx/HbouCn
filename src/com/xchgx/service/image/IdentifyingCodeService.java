package com.xchgx.service.image;

import java.awt.image.BufferedImage;

import com.xchgx.domain.image.IdentifyingCode;
import com.xchgx.domain.image.StringToImage;

import org.springframework.stereotype.Service;

@Service
public class IdentifyingCodeService {

	public String makeIdentifyingCodeImage(String imageFileSrc){
		IdentifyingCode idc = new IdentifyingCode(60*1000*10);
		StringToImage sti = new StringToImage(imageFileSrc, idc);
		return sti.makeImage();
	}
	public BufferedImage makeIdentifyingCodeBufferedImage(){
		IdentifyingCode idc = new IdentifyingCode(60*1000*10);
		StringToImage sti = new StringToImage("", idc);
		return sti.makeBufferedImage();
	}
}
