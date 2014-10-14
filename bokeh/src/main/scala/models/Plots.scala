package io.continuum.bokeh

@model class Plot extends Widget {
    object title extends Field[String]("")

    // TODO: object title_props extends Include(TextProps, prefix="title")
    // TODO: object outline_props extends Include(LineProps, prefix="outline")

    object x_range extends Field[Range]
    object y_range extends Field[Range]

    object extra_x_ranges extends Field[Map[String, Range]]
    object extra_y_ranges extends Field[Map[String, Range]]

    object x_mapper_type extends Field[String]("auto")
    object y_mapper_type extends Field[String]("auto")

    object renderers extends Field[List[Renderer]]
    object tools extends Field[List[Tool]] with ToolsField

    object left extends Field[List[PlotObject]]
    object right extends Field[List[PlotObject]]
    object above extends Field[List[PlotObject]]
    object below extends Field[List[PlotObject]]

    object toolbar_location extends Field[Location](Location.Above)

    object plot_width extends Field[Int](600)
    object plot_height extends Field[Int](600)

    object background_fill extends Field[Color](Color.White)
    object border_fill extends Field[Color](Color.White)

    object min_border_top extends Field[Int]
    object min_border_bottom extends Field[Int]
    object min_border_left extends Field[Int]
    object min_border_right extends Field[Int]
    object min_border extends Field[Int]

    object h_symmetry extends Field[Boolean](true)
    object v_symmetry extends Field[Boolean](false)
}

@model class GMapOptions extends HasFields {
    object lat extends Field[Double]
    object lng extends Field[Double]
    object zoom extends Field[Int](12)
    object map_type extends Field[MapType]
}

@model class GMapPlot extends Plot {
    object map_options extends Field[GMapOptions]
}

@model class GeoJSOptions extends HasFields {
    object lat extends Field[Double]
    object lng extends Field[Double]
    object zoom extends Field[Int](12)
}

@model class GeoJSPlot extends Plot {
    object map_options extends Field[GeoJSOptions]
}

@model class GridPlot extends Plot {
    object children extends Field[List[List[Plot]]]
    object border_space extends Field[Int](0)
}
