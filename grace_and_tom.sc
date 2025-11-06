s.boot

MIDIClient.init(1);
MIDIIn.connectAll();

/*
MIDIFunc func args
val, num, chan, and src
*/
MIDIFunc.trace(true);

/* this is how I got the device name */
MIDIClient.destinations;
m = MIDIOut(0);
m.connect(1);

/* red */
m.noteOn(144, note: 11, veloc: 5);
m.noteOff(144, note: 11, veloc: 5);

/* blue */
m.noteOn(144, note: 11, veloc: 79);
m.noteOff(144, note: 11, veloc: 79);

/* rust */
m.noteOn(144, note: 11, veloc: 106);


/*
b = Buffer.read(s, 'C:\\Users\\tim\\Desktop\\audacity\\samples\\one.mp3')
b = Buffer.read(s, '/home/ubuntu/samples/one.mp3')
~intro = Buffer.read(s, "C:/Users/tim/Desktop/audacity/samples/tank_intro.mp3");
~loop = Buffer.read(s, "C:/Users/tim/Desktop/audacity/samples/tank_bass_loop.mp3");
*/

(
var grace = Buffer.read(s, '/home/ubuntu/samples/grace.mp3');
var and = Buffer.read(s, '/home/ubuntu/samples/and.mp3');
var bob = Buffer.read(s, '/home/ubuntu/samples/bob.mp3');
var tom = Buffer.read(s, '/home/ubuntu/samples/tom.mp3');

var kick_0 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #5.wav');
var kick_1 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #3.wav');
var kick_2 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #2.wav');
var kick_3 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #9.wav');
var kick_4 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #10.wav');
var kick_5 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #4.wav');
var kick_6 = Buffer.read(s, '/home/ubuntu/dilla/Kicks/Kick #8.wav');
var snare_0 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #8.wav');
var snare_1 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #17.wav');
var snare_2 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #18.wav');
var snare_3 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #3.wav');
var snare_4 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #1.wav');
var snare_5 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #15.wav');
var snare_6 = Buffer.read(s, '/home/ubuntu/dilla/Snares/Snare #16.wav');
var hat_0 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #6.wav');
var hat_1 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #12.wav');
var hat_2 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #13.wav');
var hat_3 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #10.wav');
var hat_4 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #14.wav');
var hat_5 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #16.wav');
var hat_6 = Buffer.read(s, '/home/ubuntu/dilla/Hats/Hat #1.wav');

var samples = Dictionary.newFrom([
	81, grace,
	82, and,
	83, tom,
	84, bob,

    11, kick_0,
    12, kick_1,
    13, kick_2,
    14, kick_3,
    15, kick_4,
    16, kick_5,
    17, kick_6,
    21, snare_0,
    22, snare_1,
    23, snare_2,
    24, snare_3,
    25, snare_4,
    26, snare_5,
    27, snare_6,
    31, hat_0,
    32, hat_1,
    33, hat_2,
    34, hat_3,
    35, hat_4,
    36, hat_5,
    37, hat_6,
]);

SynthDef(\playSample, {
    |bufnum|
    var sig = PlayBuf.ar(2, bufnum, doneAction: 2); // 1 = mono
    Out.ar(0, sig!2); // play in stereo
}).add;

~noteOnFunc = MIDIFunc.noteOn({ |vel, note, chan, src|
	var bufferToPlay = samples.at(note);
	if (vel > 0 && bufferToPlay != nil) {
		Synth(\playSample, [\bufnum, bufferToPlay.bufnum]);
	}
});
);