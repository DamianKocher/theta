# Theta
Theta generates Reddit TTS TikToks.

Expect many breaking changes in the coming weeks. I wanted to get this project out as soon as possible, warts and all. The code quality is absolutely atrocious (this is my first time writing React code). If you decide to use this project, check back for updates frequently.

If you have any questions, feel free to create an issue. Please understand that this is just a personal project, so I might not implement a feature only you will use.

### Usage
 - Setup a GCP Project and enable the `text-to-speech` API
 - Tell the server where your GCP service account access file is. [More information](https://cloud.google.com/docs/authentication/getting-started)
 - Put your background MP4 videos in `/server/data/backgrounds/`
 - Create `script.json` in `/server/data/` and fill in all the fields. Check `Script.java` for required fields.
 - Start the server with `gradlew run` in `/server/`
 - Render out your MP4 by running `yarn render` in `/renderer/` 
 
 ### License
 This project is licensed under [GNU Affero General Public License v3.0](https://choosealicense.com/licenses/agpl-3.0/)
