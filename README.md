# 2019-GameStory
In this task we encourage participants to think up and investigate ways to summarize how e-sport matches ramp up, evolve and play out over time. Instead of iterating highlights, the summary needs to present an engaging and captivating story, which boils down the thrill of the game to its mere essence.

# Groups and Communications
First of all: communications. I think it’s crucial to stay in contact and communicate to overcome organizational as well as research problems. Besides the obvious possibility to send emails, we are also using discord, so please connect to us there too if you feel like it :) https://discord.gg/THEpZFe

Also use Github: We've got this very github repo to exchange information and code. Please use the issue tracker for issues with the data or the whole projects, we'll try to resolve them asap. Send us pull requests if want us to include additional code or documentation.

# Registration and Submission

## Register for MediaEval
Task registration is now open. Check out the descriptions of this year's tasks on the [MediaEval 2019 webpage](http://www.multimediaeval.org/mediaeval2019/index.html), and then [register to participate](https://docs.google.com/forms/d/e/1FAIpQLSfxS4LPBhLQUTXSPT5vogtiSy7BuAKrPs6u6pZXcSV1Xs7XEQ/viewform). The workshop will be held 27-29 October 2019 (near Nice, right after ACM Multimedia 2019.)

If you have any questions, don’t hesitate to contact us on discord (https://discord.gg/THEpZFe), via mail or any other means necessary!

# Data
The data consists of training and test data set (sse below). Both consist of videos as well as additional describing the ongoings in the videos

## Video streams
There are 12 different streams labeled P1 - P12. P is short for player. 
There are 10 players in a CS:GO match, 5 in each of the two teams. 
P1 - P10 are each of these player's perspective in the match. 
P11 is the event stream, where the pre-show, interviews, commentator view etc. is shown. P12 is only showing the map of the matches and is without audio. 

Each of these streams are up during the full day, and therefore there are 12 video files for each day.  The training set includes the matches from 2018-03-02 and 2018-03-04. 

## Meta Data
The file metadata.csv contains information about the streams. Timestamps and durations of matches during the days and in what stream file the match is. Each column in the csv table is described in below.

***ID*** - An alphanumeric id of the clip that the row represents.

***start_time*** - Number of seconds into the day stream file when this clip starts.

***duration*** - Number of seconds that this clip last.

***type*** - The clips in this dataset have a descriptive type. For example it could be labeled match, meaning that the clip is the full match stream that is showed when users select to watch a match at Znipe TV. There are a few more interesting types and as the number of clips are not that many, we encourange you to investigate the different types to get inspiration of what you can do. 

***match_id*** The train set contains 11 matches. Each clip belongs to one of these 11 matches and the match_id is a number between 1 and 11.

***perspective*** - Which of the P1-P12 the clip is from.

***stream_file*** - Which video stream file the clip exist in.

***stream_timestamp*** - The same as the start_time but in the human readable format HH:MM:SS to be able to easier manually jump to the clip in the stream file for investigation.

***UTC_timestamp*** - The UTC timestamp of the starting time in the clip. This can be used to synchronize the video stream time with the timestamps from the game logs described below. 

## Game logs
In the timeline folder, a game log exist for each match in the dataset.
The name of the files matches the match_id in metadata.csv.

Each game log is a json object that includes events such as what each player purchased, who killed whom and where. All actions have UTC timestamps. 
These timestamps may unfortunately differ up to 40 seconds from the UTC timestamps in the metadata.csv.

## Synchronization data
Due to above problems we created with the help of Michael Wutti, a participant from last year's GameStory task, synchronization data files that can be used to synchronize the video sreams P1-P11. The files scores-test.csv and scores-training.csv (for test and training data respectively) give the match id, the round number as well as the score after finishing the particular round. 

The sync data defines sync points per match and video stream, e.g. sync_match_1_P1.csv would define the sync points for the P1 stream for the first match. Please note that the sync points are created automatically and do not always match completeley, especially the first rounds of each match are not properly synchronized as the warm-up phase is not explicitely recognized. Also, for P11 the replays interfere with the sync points. If the algorithm is unsure the key word ''none'' is used instead of an actual value. For the 10th match in the training data no sync points are available due to missing server logs.  

The sync files give 

  * **match**	 .. the match id 
  * **round**  .. the round number
  * **round_begin** .. hh:mm:ss of the beginning of the round relative to the beginning of the video stream
  * **frame_nr** .. the frame number of the start of the round

You can find the original code and data at  https://github.com/mwutti/medieval18 with the original extraction scripts for 
  * sync: https://github.com/mwutti/medieval18/blob/master/sync_points_extractor.py
  * scores: https://github.com/mwutti/medieval18/blob/master/score_extractor.py

## Training data
The training data (~65 GB) will be released on May 31st 2019.

## Test data
The test data (~33GB) is the actual data set to find the replays and generate the summary. It will be release on June 30th 2019.

