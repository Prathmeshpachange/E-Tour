using Microsoft.EntityFrameworkCore;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public class PackageRepository : IPackageRepository
    {
        private readonly SafarnamadbContext _context;

        public PackageRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Package>> GetAllPackages()
        {
            return await _context.Packages
                .Include(p => p.Category)
                .Include(p => p.Subcategory)
                .Include(p => p.Costs)
                .Include(p => p.Itineraries)
                .Include(p => p.Departures)
                .ToListAsync();
        }

        public async Task<Package?> GetPackageById(int id)
        {
            return await _context.Packages
                .Include(p => p.Category)
                .Include(p => p.Subcategory)
                .Include(p => p.Costs)
                .Include(p => p.Itineraries)
                .Include(p => p.Departures)
                .FirstOrDefaultAsync(p => p.PackageId == id);
        }

        public async Task AddPackage(Package package)
        {
            _context.Packages.Add(package);
            await _context.SaveChangesAsync();
        }

        public async Task UpdatePackage(Package package)
        {
            _context.Packages.Update(package);
            await _context.SaveChangesAsync();
        }

        public async Task DeletePackage(int id)
        {
            var package = await _context.Packages.FindAsync(id);
            if (package != null)
            {
                _context.Packages.Remove(package);
                await _context.SaveChangesAsync();
            }
        }

        public async Task<bool> PackageExists(int id)
        {
            return await _context.Packages.AnyAsync(e => e.PackageId == id);
        }

        public async Task<IEnumerable<Package>> GetBySubcategoryIdAsync(int subcategoryId)
        {
            return await _context.Packages
                .Where(p => p.SubcategoryId == subcategoryId)
                .ToListAsync();
        }


        public async Task<List<Package>> GetPackagesByCategoryAsync(int categoryId)
        {
            var category = await _context.Categories.FirstOrDefaultAsync(c => c.CategoryId == categoryId);
            if (category == null)
                return new List<Package>();

            if (category.Flag == true)
            {
                // Special category: packages directly linked to category
                return await _context.Packages
                    .Where(p => p.CategoryId == categoryId)
                    .ToListAsync();
            }
            else
            {
                // Normal category: get packages through subcategories
                var subcategoryIds = await _context.Subcategories
                    .Where(sc => sc.CategoryId == categoryId)
                    .Select(sc => sc.SubcategoryId)
                    .ToListAsync();

                return await _context.Packages
                    .Where(p => subcategoryIds.Contains(p.SubcategoryId ?? 0))
                    .ToListAsync();
            }
        }
    }
}
