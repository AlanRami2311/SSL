// sort_demo.zig — Ejemplo de ordenamiento nativo en Zig
// Compilar: zig build-exe sort_demo.zig
// Ejecutar: ./sort_demo
// Versión: Zig 0.12+ / 0.13

const std = @import("std");

pub fn main() !void {
    const stdout = std.io.getStdOut().writer();

    // ─── 1. Sort básico de enteros ───────────────────────────────
    try stdout.print("\n=== Sort básico (i32) ===\n", .{});

    var nums = [_]i32{ 5, 2, 8, 1, 9, 3, 7, 4, 6 };
    try stdout.print("Antes: {any}\n", .{nums});

    // std.mem.sort: sort inestable (PDQ Sort internamente)
    // T: tipo comptime, slice: []T, ctx: {}, cmpFn: función de comparación
    std.mem.sort(i32, &nums, {}, comptime std.sort.asc(i32));

    try stdout.print("Después (asc): {any}\n", .{nums});

    std.mem.sort(i32, &nums, {}, comptime std.sort.desc(i32));
    try stdout.print("Después (desc): {any}\n", .{nums});

    // ─── 2. Sort de strings ──────────────────────────────────────
    try stdout.print("\n=== Sort de strings ===\n", .{});

    var palabras = [_][]const u8{ "banana", "apple", "cherry", "date", "elderberry" };
    try stdout.print("Antes: ", .{});
    for (palabras) |p| try stdout.print("{s} ", .{p});
    try stdout.print("\n", .{});

    std.mem.sort([]const u8, &palabras, {}, struct {
        fn lessThan(_: void, a: []const u8, b: []const u8) bool {
            return std.mem.lessThan(u8, a, b);
        }
    }.lessThan);

    try stdout.print("Después: ", .{});
    for (palabras) |p| try stdout.print("{s} ", .{p});
    try stdout.print("\n", .{});

    // ─── 3. Sort de structs con función personalizada ────────────
    try stdout.print("\n=== Sort de structs ===\n", .{});

    const Persona = struct {
        nombre: []const u8,
        edad: u32,
    };

    var personas = [_]Persona{
        .{ .nombre = "Carlos", .edad = 30 },
        .{ .nombre = "Ana",    .edad = 25 },
        .{ .nombre = "Beatriz",.edad = 35 },
        .{ .nombre = "Diego",  .edad = 28 },
    };

    // Sort por edad (ascendente) — función de comparación personalizada
    std.mem.sort(Persona, &personas, {}, struct {
        fn lessThan(_: void, a: Persona, b: Persona) bool {
            return a.edad < b.edad;
        }
    }.lessThan);

    try stdout.print("Por edad: ", .{});
    for (personas) |p| try stdout.print("{s}({d}) ", .{ p.nombre, p.edad });
    try stdout.print("\n", .{});

    // Sort estable: std.sort.block (preserva orden de elementos iguales)
    // útil cuando queremos sort estable garantizado
    // std.sort.block(Persona, &personas, {}, lessThan);

    // ─── 4. Sort parcial (subslice) ──────────────────────────────
    try stdout.print("\n=== Sort parcial (elementos 2 a 5) ===\n", .{});

    var arr = [_]i32{ 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    try stdout.print("Antes: {any}\n", .{arr});

    // Solo ordenamos el subslice arr[2..6]
    std.mem.sort(i32, arr[2..6], {}, comptime std.sort.asc(i32));
    try stdout.print("Después (sort parcial [2..6]): {any}\n", .{arr});

    // ─── 5. Verificar si está ordenado ───────────────────────────
    try stdout.print("\n=== Verificación ===\n", .{});

    var sorted = [_]i32{ 1, 2, 3, 4, 5 };
    var unsorted = [_]i32{ 3, 1, 4, 1, 5 };

    try stdout.print("¿[1,2,3,4,5] está ordenado? {}\n", .{
        std.sort.isSorted(i32, &sorted, {}, std.sort.asc(i32))
    });
    try stdout.print("¿[3,1,4,1,5] está ordenado? {}\n", .{
        std.sort.isSorted(i32, &unsorted, {}, std.sort.asc(i32))
    });

    try stdout.print("\n✓ Demo completado\n\n", .{});
}
