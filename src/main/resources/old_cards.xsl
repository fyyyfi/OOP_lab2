<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:key name="cards-by-country" match="OldCard" use="Country"/>

    <xsl:template match="/OldCards">
        <CardsGroupedByCountry>
            <xsl:for-each select="OldCard[generate-id() = generate-id(key('cards-by-country', Country)[1])]">
                <Group country="{Country}">
                    <xsl:copy-of select="key('cards-by-country', Country)"/>
                </Group>
            </xsl:for-each>
        </CardsGroupedByCountry>
    </xsl:template>
</xsl:stylesheet>