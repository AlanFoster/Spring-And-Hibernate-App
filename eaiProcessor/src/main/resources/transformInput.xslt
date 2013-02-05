<?xml version="1.0" encoding="UTF-8"?>
<!-- This transform is used to convert the very poor legacy XML into a format that is usable by the new Employee System -->
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
        xmlns:foo="http://www.foobarbaz.com/"
        exclude-result-prefixes="foo"
        >

    <xsl:output method="xml" indent="yes"/>

    <!-- Match the root note -->
    <xsl:template match="/foo:people">
        <employees>
            <!-- Apply the templates on the selected root note -->
            <xsl:apply-templates/>
        </employees>
    </xsl:template>

    <!-- Template match every 'persons' element, in the namespace 'foo' -->
    <xsl:template match="foo:persons">
        <!-- Extract all of the common variables we wish to make use of later -->
        <xsl:variable name="name" select="./foo:name/@content"/>
        <!-- Get the first from the concatenated name -->
        <xsl:variable name="firstName">
            <xsl:call-template name="outputNthToken">
                <xsl:with-param name="currentString" select="$name"></xsl:with-param>
                <xsl:with-param name="desiredNthToken" select="1"></xsl:with-param>
            </xsl:call-template>
        </xsl:variable>
        <!-- Get the second from the concatenated name -->
        <xsl:variable name="secondName">
            <xsl:call-template name="outputNthToken">
                <xsl:with-param name="currentString" select="$name"></xsl:with-param>
                <xsl:with-param name="desiredNthToken" select="2"></xsl:with-param>
            </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="job" select="./foo:job"/>
        <xsl:variable name="deskId" select="./foo:desk/text()"/>

        <!-- Create the BatchProcessor employee elements -->
        <employee>
            <deskId>
                <xsl:value-of select="$deskId"/>
            </deskId>
            <firstName>
                <xsl:value-of select="$firstName"/>
            </firstName>
            <job>
                <jobId>
                    <xsl:value-of select="$job/@id"/>
                </jobId>
                <jobTitle>
                    <xsl:value-of select="$job/@title"/>
                </jobTitle>
            </job>
            <secondName>
                <xsl:value-of select="$secondName"/>
            </secondName>
        </employee>
    </xsl:template>

    <!-- Recursive function to output the Nth token of a string, default delimiter is an underscore
      Note; In order to keep with XSLT conventions this is a 1 based mechanism. IE outputNthToken('a_b', 1) => 'a'
       (I don't know why I did this - using xslt 2.0 would be a much smarter choice too) -->
    <xsl:template name="outputNthToken">
        <xsl:param name="currentString"/>
        <xsl:param name="desiredNthToken"/>
        <xsl:param name="currentNthToken" select="1"/>
        <xsl:param name="deliminator" select="_"/>
        <xsl:variable name="currentToken">
            <xsl:choose>
                <xsl:when test="contains($currentString, '_')">
                    <xsl:value-of select="substring-before($currentString, '_')"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$currentString"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:variable name="remainingString" select="substring-after($currentString, '_')"/>

        <!-- Base case, we are finished if we have reached the desired token.
              If there is string left, recursively call ourselves with the remaining string and increase our count by one
              Otherwise the nth token didn't exist in the string -->
        <xsl:choose>
            <xsl:when test="$desiredNthToken = $currentNthToken">
                <xsl:value-of select="$currentToken"/>
            </xsl:when>
            <xsl:when test="$remainingString != ''">
                <xsl:call-template name="outputNthToken">
                    <xsl:with-param name="currentString" select="$remainingString"></xsl:with-param>
                    <xsl:with-param name="desiredNthToken" select="$desiredNthToken"></xsl:with-param>
                    <xsl:with-param name="currentNthToken" select="$currentNthToken + 1"/>
                </xsl:call-template>
            </xsl:when>
            <!-- We've fallen through,  -->
            <xsl:otherwise>
                <!-- Throw Exception -->
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>