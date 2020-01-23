ENCODING = ./encoding_class.py
DECODING = ./decoding_class.py

FONT = ./fonte-linux64
CHANNEL = ./canal-linux64

# File to store the output files
DIRECTORY="Output_Files"

NUMBER_WORDS = 6
CHANNEL_N = $$(( 2 * 8 * $(NUMBER_WORDS) ))
exe0 = $(FONT) $(NUMBER_WORDS) | $(ENCODING) | $(DECODING)
exe1 = $(FONT) $(NUMBER_WORDS) | $(ENCODING) | $(CHANNEL) $(CHANNEL_N) | $(DECODING)


clean:
		rm -rf $(DIRECTORY)
		rm -rf __pycache__

setup:
		mkdir -p $(DIRECTORY)

all:	setup
		$(exe0)
