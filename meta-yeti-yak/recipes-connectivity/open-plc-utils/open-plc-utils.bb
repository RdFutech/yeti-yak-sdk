

DESCRIPTION = "Open PLC utils"
HOMEPAGE = "https://github.com/qca/open-plc-utils"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7d83a9e9a9788beb9357262af385f6c7"


# Keep work dir for easier inspection during bring-up
PACKAGE_STRIP = "no"
RM_WORK_EXCLUDE += "${PN}"


# Fetch upstream
SRC_URI = "git://github.com/qca/open-plc-utils.git;protocol=https;branch=master"
SRCREV = "358dfcf78bdaf7b0b13dcdf91cb1aae1789f2770"


S = "${WORKDIR}/git"


inherit pkgconfig


DEPENDS = "ncurses gcc libnetfilter-conntrack libpcap libnl flex bison bison-native zlib libsodium liburcu libnet"


# Ensure we cross-compile and define the chipset; avoid host compiler.
EXTRA_OEMAKE = ' \
CC="${CC}" \
AR="${AR}" \
LD="${LD}" \
RANLIB="${RANLIB}" \
STRIP="${STRIP}" \
CPPFLAGS="${CPPFLAGS} -D_GNU_SOURCE -D__GETOPT_H__ -D_GETOPT_DEFINED_" \
CFLAGS="${CFLAGS} -DMAKEFILE -DAR7420 -DAR7400" \
LDFLAGS="${LDFLAGS}" \
'


# Build only plctool to keep it simple; switch to full build later if desired.
do_compile() {
oe_runmake -C plc plctool
}


# Install the binary
#do_install:append() is fine too; using do_install here for clarity


do_install() {
install -d ${D}${bindir}
install -m 0755 ${S}/plc/plctool ${D}${bindir}/
}


# Package contents
FILES:${PN} += "${bindir}/plctool"