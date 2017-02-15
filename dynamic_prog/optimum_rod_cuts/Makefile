CXX = g++-4.9									# compiler
CXXFLAGS = -g -Wall -Werror -std=c++14 -MMD				# compiler flags
MAKEFILE_NAME = ${firstword ${MAKEFILE_LIST}}			# makefile name

OBJECTS = rod.o driver.o
EXEC = rod						# executable name
DEPENDS = rod.d driver.d 

.PHONY : all clean

all : ${EXECS}

${EXEC} : ${OBJECTS}								# link step
	${CXX} $^ -o $@

${OBJECTS} : ${MAKEFILE_NAME}							# OPTIONAL : changes to this file => recompile

-include ${DEPENDS}								# include *.d files containing program dependences

clean :										# remove files that can be regenerated
	rm -f ${DEPENDS} ${OBJECTS} ${EXEC}

