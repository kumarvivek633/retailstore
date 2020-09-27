package com.vivek.retailstore.domain.shoppingcounter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SetupNewShoppingCounterCommand{

    @NonNull
    private String counterName;

}
