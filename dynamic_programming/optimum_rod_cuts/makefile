#
# Use gcc flag -MMD (user) or -MD (user/system) to generate dependences among source files.
# Use make default rules for commonly used file-name suffixes and make variables names.
#
# % make

CXX = g++-4.9
CXXFLAGS = -g -Wall -std=c++14 -MMD
MAKEFILE_NAME = ${firstword ${MAKEFILE_LIST}}

OBJECTS = driver.o rod.o
DEPENDS = ${OBJECTS:.o=.d}
EXEC = rod

#######TARGETS#############

.PHONY : clean

${EXEC} : ${OBJECTS}			# link step
	${CXX} ${CXXFLAGS} $^ -o $@	# additional object files before $^

${OBJECTS} : ${MAKEFILE_NAME}           # OPTIONAL : changes to this file => recompile

# make implicitly generates rules to compile C++ files that generate .o files

-include ${DEPENDS}                     # include *.d files containing program dependencies

clean :	
	rm -f ${OBJECTS} ${DEPENDS} ${EXEC}

