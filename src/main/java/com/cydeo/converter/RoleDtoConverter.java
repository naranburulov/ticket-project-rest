package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {

    private final RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {
        if (source == null || source.equals("")){   //Select -> ""
            return null;                //give me null, so that @NotNull validation from UserDTO worked
            //to prevent choosing "select" in the dropdown
        }
        return roleService.findById(Long.parseLong(source));
    }
}
