import os from "os";
import { Config } from "remotion";

Config.Output.setOverwriteOutput(false);

Config.Rendering.setConcurrency(Math.ceil(os.cpus().length / 4));

Config.Rendering.setImageFormat("jpeg");
Config.Rendering.setQuality(100);

Config.Rendering.setScale(2);

Config.Output.setCodec("h264");
Config.Output.setCrf(16);
