def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()

def html_prefix = '''
<!DOCTYPE html><meta charset="utf-8"><html>
<head>
    <script src="http://d3js.org/d3.v4.min.js"></script>
    <script src="http://viz-js.com/bower_components/viz.js/viz-lite.js"></script>
    <script src="https://github.com/magjac/d3-graphviz/releases/download/v0.1.2/d3-graphviz.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <style>div {text-align: center;width:100%;}</style>   
</head>
<body><div id="graph"></div>
<script>
'''
println ${html_prefix}

print "d3.select('#graph').graphviz().renderDot('"
print "digraph test {"
print "rankdir=LR;"
print "node [shape = box,height=.1,fontsize=48,fontname=Helvetica,fillcolor=yellow,style=filled];"
plugins.each {
    def plugin = it.getShortName()
    print "\"${plugin}\";"
    def deps =  it.getDependencies()
    deps.each {
      def s = it.shortName
      print "\"${plugin}\" -> \"${s}\";"
    }
} 
print "}');"

println "</script></body></html>"