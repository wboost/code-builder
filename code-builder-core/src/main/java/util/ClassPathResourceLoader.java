package util;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.slf4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import top.wboost.common.log.util.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResourceLoader extends ResourceLoader {

    private static final Logger LOGGER = LoggerUtil.getLogger(ClassPathResourceLoader.class);

    private org.springframework.core.io.ResourceLoader resourceLoader = new DefaultResourceLoader();


    @Override
    public void init(ExtendedProperties configuration) {

    }

    @Override
    public InputStream getResourceStream(String source) throws ResourceNotFoundException {
        org.springframework.core.io.Resource resource =
                this.resourceLoader.getResource(source);
        try {
            return resource.getInputStream();
        } catch (IOException ex) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Could not find Velocity resource: " + resource);
            }
        }
        throw new ResourceNotFoundException(
                "Could not find resource [" + source + "] in Spring resource loader path");
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return false;
    }

    @Override
    public long getLastModified(Resource resource) {
        return 0;
    }

}