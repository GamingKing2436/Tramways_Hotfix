package com.gamingking2436.mixins;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import purplecreate.tramways.content.announcements.util.TTS;;

@Mixin(TTS.class)
public class TTSMixin {
	@Shadow(remap = false)
	private final Consumer<byte[]> onData;

	public TTSMixin(String voice, String content, Consumer<byte[]> onData) {
		this.onData = onData;
	}
	@WrapMethod(method ="start", remap = false)
	public void start(Operation<Void> original) {
		try {
		original.call();
		} catch (CompletionException e) {
			onData.accept(null);
        }
	}
}
