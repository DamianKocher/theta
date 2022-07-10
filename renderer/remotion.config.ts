import os from 'os';
import { Config } from 'remotion';

Config.Output.setOverwriteOutput(true);

Config.Rendering.setConcurrency(os.cpus().length - 1); 

Config.Rendering.setImageFormat('jpeg');
Config.Rendering.setQuality(100);

Config.Rendering.setScale(2);

Config.Output.setCodec("h264");
Config.Output.setCrf(17);