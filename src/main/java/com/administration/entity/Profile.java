package com.administration.entity;

import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Profile implements Serializable {
    @Id
    private String idProfile;
    private String nomProfile;

}
