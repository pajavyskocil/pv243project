package cz.fi.muni.TACOS.service.Impl;

import org.dozer.DozerBeanMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@ApplicationScoped
public class MapperImpl extends DozerBeanMapper {

    public MapperImpl() {
        super();

        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");
        setMappingFiles(mappingFiles);
    }
}
