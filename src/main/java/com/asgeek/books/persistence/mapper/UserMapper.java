package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({})
    UserAuth toUserAuth(User user);

    @InheritInverseConfiguration
    @Mappings({})
    User toUser(UserAuth userAuth);
}
