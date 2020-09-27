package com.vivek.retailstore.domain.shoppingcart;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CheckoutShoppingCartCommand {

	@NonNull
	private Long shoppingCounterId;

	@NonNull
	private Long shoppingCartId;
}
