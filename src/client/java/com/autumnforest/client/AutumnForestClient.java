package com.autumnforest.client;

import net.fabricmc.api.ClientModInitializer;

public class AutumnForestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// test
		System.out.println("[AutumnForest] Client mod initialized!");
	}
}