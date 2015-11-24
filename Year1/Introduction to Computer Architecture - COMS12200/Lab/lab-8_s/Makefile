SOURCES = $(wildcard hp35-rom_*.txt)
TARGETS = $(patsubst %.txt, %.bin, ${SOURCES})

${TARGETS} : %.bin : %.txt
	@cat ${<} | grep -v '#' | python convert.py > ${@}	

all   : ${TARGETS}

clean :
	@rm -f *.bin
