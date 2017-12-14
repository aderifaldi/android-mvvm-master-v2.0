package com.ade.mvvm.model.local;

import io.realm.RealmObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class ProductCache extends RealmObject {

    private String json;

}
