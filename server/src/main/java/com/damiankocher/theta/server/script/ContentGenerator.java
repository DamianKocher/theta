package com.damiankocher.theta.server.script;

import com.damiankocher.theta.server.Theta;
import com.damiankocher.theta.server.content.Content;
import org.jetbrains.annotations.NotNull;

public interface ContentGenerator {

    @NotNull Content createContent(final @NotNull Theta theta);

}
