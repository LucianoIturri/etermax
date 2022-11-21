package org.example.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
public class BearerToken {
    private String token;

    public String toString(){
        return token;
    }
}
