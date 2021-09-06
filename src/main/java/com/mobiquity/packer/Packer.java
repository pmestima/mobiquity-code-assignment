package com.mobiquity.packer;

import com.mobiquity.dto.PackageDto;
import com.mobiquity.exception.APIException;

import java.util.List;

public class Packer {

    private Packer() { }

    public static String pack(String filePath) throws APIException {
        List<PackageDto> packages = Parser.parseFile(filePath);
        return new PackerProcessor().process(packages);
    }

}
