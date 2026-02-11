/**
 * Copyright (C) Telicent Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.telicent.jena.graphql.schemas.telicent.graph.models;

/**
 * Represents style metadata for a class.
 */
public class Style {

    private Shape shape;
    private String faIconClass;
    private String faIconUnicode;
    private String faIconClassFree;
    private String faIconUnicodeFree;
    private String lineColor;
    private String fillColor;
    private String iconColor;
    private String darkModeLineColor;
    private String darkModeFillColor;
    private String darkModeIconColor;
    private String description;

    /**
     * Creates a new style instance.
     */
    public Style() {
    }

    /**
     * Gets the shape.
     *
     * @return Shape value
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Sets the shape.
     *
     * @param shape Shape value
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Gets the Font Awesome icon class.
     *
     * @return Icon class
     */
    public String getFa_icon_class() {
        return faIconClass;
    }

    /**
     * Sets the Font Awesome icon class.
     *
     * @param faIconClass Icon class
     */
    public void setFa_icon_class(String faIconClass) {
        this.faIconClass = faIconClass;
    }

    /**
     * Gets the Font Awesome icon unicode.
     *
     * @return Icon unicode
     */
    public String getFa_icon_unicode() {
        return faIconUnicode;
    }

    /**
     * Sets the Font Awesome icon unicode.
     *
     * @param faIconUnicode Icon unicode
     */
    public void setFa_icon_unicode(String faIconUnicode) {
        this.faIconUnicode = faIconUnicode;
    }

    /**
     * Gets the free Font Awesome icon class.
     *
     * @return Free icon class
     */
    public String getFa_icon_class_free() {
        return faIconClassFree;
    }

    /**
     * Sets the free Font Awesome icon class.
     *
     * @param faIconClassFree Free icon class
     */
    public void setFa_icon_class_free(String faIconClassFree) {
        this.faIconClassFree = faIconClassFree;
    }

    /**
     * Gets the free Font Awesome icon unicode.
     *
     * @return Free icon unicode
     */
    public String getFa_icon_unicode_free() {
        return faIconUnicodeFree;
    }

    /**
     * Sets the free Font Awesome icon unicode.
     *
     * @param faIconUnicodeFree Free icon unicode
     */
    public void setFa_icon_unicode_free(String faIconUnicodeFree) {
        this.faIconUnicodeFree = faIconUnicodeFree;
    }

    /**
     * Gets the line color.
     *
     * @return Line color
     */
    public String getLine_color() {
        return lineColor;
    }

    /**
     * Sets the line color.
     *
     * @param lineColor Line color
     */
    public void setLine_color(String lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Gets the fill color.
     *
     * @return Fill color
     */
    public String getFill_color() {
        return fillColor;
    }

    /**
     * Sets the fill color.
     *
     * @param fillColor Fill color
     */
    public void setFill_color(String fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Gets the icon color.
     *
     * @return Icon color
     */
    public String getIcon_color() {
        return iconColor;
    }

    /**
     * Sets the icon color.
     *
     * @param iconColor Icon color
     */
    public void setIcon_color(String iconColor) {
        this.iconColor = iconColor;
    }

    /**
     * Gets the dark mode line color.
     *
     * @return Dark mode line color
     */
    public String getDark_mode_line_color() {
        return darkModeLineColor;
    }

    /**
     * Sets the dark mode line color.
     *
     * @param darkModeLineColor Dark mode line color
     */
    public void setDark_mode_line_color(String darkModeLineColor) {
        this.darkModeLineColor = darkModeLineColor;
    }

    /**
     * Gets the dark mode fill color.
     *
     * @return Dark mode fill color
     */
    public String getDark_mode_fill_color() {
        return darkModeFillColor;
    }

    /**
     * Sets the dark mode fill color.
     *
     * @param darkModeFillColor Dark mode fill color
     */
    public void setDark_mode_fill_color(String darkModeFillColor) {
        this.darkModeFillColor = darkModeFillColor;
    }

    /**
     * Gets the dark mode icon color.
     *
     * @return Dark mode icon color
     */
    public String getDark_mode_icon_color() {
        return darkModeIconColor;
    }

    /**
     * Sets the dark mode icon color.
     *
     * @param darkModeIconColor Dark mode icon color
     */
    public void setDark_mode_icon_color(String darkModeIconColor) {
        this.darkModeIconColor = darkModeIconColor;
    }

    /**
     * Gets the style description.
     *
     * @return Style description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the style description.
     *
     * @param description Style description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
